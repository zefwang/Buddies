import tester.*;

// runs tests for the buddies problem
class ExamplesBuddies {
  Person ann;
  Person bob;
  Person cole;
  Person dan;
  Person ed;
  Person fay;
  Person gabi;
  Person hank;
  Person jan;
  Person kim;
  Person len;

  void initBuddies() {
    this.ann = new Person("Ann");
    this.bob = new Person("Bob");
    this.cole = new Person("Cole");
    this.dan = new Person("Dan");
    this.ed = new Person("Ed");
    this.fay = new Person("Fay");
    this.gabi = new Person("Gabi");
    this.hank = new Person("Hank");
    this.jan = new Person("Jan");
    this.kim = new Person("Kim");
    this.len = new Person("Len");
    this.ann.addManyBuddies(new ConsLoBuddy(this.bob, new ConsLoBuddy(this.cole, new MTLoBuddy())));
    this.bob.addManyBuddies(new ConsLoBuddy(this.ann,
        new ConsLoBuddy(this.ed, new ConsLoBuddy(this.hank, new MTLoBuddy()))));
    this.cole.addBuddy(this.dan);
    this.dan.addBuddy(this.cole);
    this.ed.addBuddy(this.fay);
    this.fay.addManyBuddies(new ConsLoBuddy(this.ed, new ConsLoBuddy(this.gabi, new MTLoBuddy())));
    this.gabi.addManyBuddies(new ConsLoBuddy(this.ed, new ConsLoBuddy(this.fay, new MTLoBuddy())));
    this.jan.addManyBuddies(new ConsLoBuddy(this.kim, new ConsLoBuddy(this.len, new MTLoBuddy())));
    this.kim.addManyBuddies(new ConsLoBuddy(this.jan, new ConsLoBuddy(this.len, new MTLoBuddy())));
    this.len.addManyBuddies(new ConsLoBuddy(this.jan, new ConsLoBuddy(this.kim, new MTLoBuddy())));
  }

  void testAddBuddy(Tester t) { // Tests that addBuddy() and addManyBuddies() work
    this.ann = new Person("Ann");
    this.bob = new Person("Bob");
    this.dan = new Person("Dan");

    ann.addBuddy(this.dan);
    t.checkExpect(this.ann.buddies, new ConsLoBuddy(this.dan, new MTLoBuddy()));
    ann.addBuddy(this.bob);
    t.checkExpect(this.ann.buddies,
        new ConsLoBuddy(this.bob, new ConsLoBuddy(this.dan, new MTLoBuddy())));

    this.ann = new Person("Ann");
    ann.addManyBuddies(new ConsLoBuddy(this.bob, new ConsLoBuddy(this.dan, new MTLoBuddy())));
    t.checkExpect(this.ann.buddies,
        new ConsLoBuddy(this.dan, new ConsLoBuddy(this.bob, new MTLoBuddy())));

    this.ann = new Person("Ann");
    new ConsLoBuddy(this.bob, new ConsLoBuddy(this.dan, new MTLoBuddy())).addMultBuddies(this.ann);
    t.checkExpect(this.ann.buddies,
        new ConsLoBuddy(this.dan, new ConsLoBuddy(this.bob, new MTLoBuddy())));
  }

  void testHasDirectBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.cole), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.ed), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.kim), false);
    t.checkExpect(this.hank.hasDirectBuddy(this.bob), false);
  }

  void testSamePerson(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.samePerson(this.ann), true);
    t.checkExpect(this.ann.samePerson(this.bob), false);

  }

  void testCountCommonBuddies(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.ann.countCommonBuddies(this.hank), 0);
  }

  void testHasExtendedBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(dan), true);
    t.checkExpect(this.ann.hasExtendedBuddy(bob), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(ed), true);
    t.checkExpect(this.ann.hasExtendedBuddy(cole), true);
    t.checkExpect(this.ann.hasExtendedBuddy(jan), false);
    t.checkExpect(this.jan.hasExtendedBuddy(len), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(bob), false);
    t.checkExpect(this.hank.hasExtendedBuddy(kim), false);
  }

  void testHasExtendedBuddyHelper(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddyHelper(dan, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(bob, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.hasExtendedBuddyHelper(ed, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(cole, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(jan, new MTLoBuddy()), false);
    t.checkExpect(this.jan.hasExtendedBuddyHelper(len, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.hasExtendedBuddyHelper(bob, new MTLoBuddy()), false);
    t.checkExpect(this.hank.hasExtendedBuddyHelper(kim, new MTLoBuddy()), false);
  }

  void testPartyCount(Tester t) {
    initBuddies();
    t.checkExpect(ann.partyCount(), 8);
    t.checkExpect(ed.partyCount(), 3);
    t.checkExpect(gabi.partyCount(), 3);
    t.checkExpect(hank.partyCount(), 1);
    t.checkExpect(len.partyCount(), 3);
  }

  void testPartyCountHelper(Tester t) {
    initBuddies();
    t.checkExpect(ann.partyCountHelper(new ConsLoBuddy(this.ann, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 8);
    t.checkExpect(
        ed.partyCountHelper(new ConsLoBuddy(this.ed, new MTLoBuddy())).countUnique(new MTLoBuddy()),
        3);
    t.checkExpect(gabi.partyCountHelper(new ConsLoBuddy(this.gabi, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
    t.checkExpect(hank.partyCountHelper(new ConsLoBuddy(this.hank, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 1);
    t.checkExpect(len.partyCountHelper(new ConsLoBuddy(this.len, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
  }

}