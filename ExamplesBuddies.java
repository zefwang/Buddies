import tester.*;

// runs tests for the buddies problem
public class ExamplesBuddies {
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

  void reset() {
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

  void testHasDirectBuddy(Tester t) {
    reset();
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.cole), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.ed), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.kim), false);
    t.checkExpect(this.hank.hasDirectBuddy(this.bob), false);
  }

  void testCountCommonBuddies(Tester t) {
    reset();
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.ann.countCommonBuddies(this.hank), 0);
  }

  void testHasExtendedBuddy(Tester t) {
    reset();
    t.checkExpect(this.ann.hasExtendedBuddy(dan), true);
    t.checkExpect(this.ann.hasExtendedBuddy(bob), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(ed), true);
    t.checkExpect(this.ann.hasExtendedBuddy(cole), true);
    t.checkExpect(this.ann.hasExtendedBuddy(jan), false);
    t.checkExpect(this.jan.hasExtendedBuddy(len), true);
    t.checkExpect(this.gabi.hasExtendedBuddy(bob), false);
    t.checkExpect(this.hank.hasExtendedBuddy(kim), false);
  }

  void testPartyCount(Tester t) {
    reset();
    t.checkExpect(ann.partyCount(), 8);
    t.checkExpect(ed.partyCount(), 3);
    t.checkExpect(hank.partyCount(), 1);
    t.checkExpect(jan.partyCount(), 4); // DOESN'T WORK YET
  }
}