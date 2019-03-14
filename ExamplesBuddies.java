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
  Person personA;
  Person personB;
  Person personC;
  Person personD;
  Person personE;

  // Initial values
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

    this.personA = new Person("A", .95, .8);
    this.personB = new Person("B", .85, .99);
    this.personC = new Person("C", .95, .9);
    this.personD = new Person("D", 1.0, .95);
    this.personE = new Person("E", .98, .95);

    this.personA
        .addManyBuddies(new ConsLoBuddy(personB, new ConsLoBuddy(personC, new MTLoBuddy())));
    this.personB.addBuddy(this.personD);
    this.personC.addBuddy(this.personD);
  }

  // Tests for addBuddy(), addManyBuddies(), and addMultBuddies()
  void testAddBuddy(Tester t) {
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

  // Tests for the HasDirectBuddy() and hasDirect() methods
  void testHasDirectBuddy(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.cole), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.ed), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.kim), false);
    t.checkExpect(this.hank.hasDirectBuddy(this.bob), false);

    t.checkExpect(this.ann.buddies.hasDirect(this.bob), true);
    t.checkExpect(this.ann.buddies.hasDirect(this.cole), true);
    t.checkExpect(this.ann.buddies.hasDirect(this.ed), false);
    t.checkExpect(this.ann.buddies.hasDirect(this.kim), false);
    t.checkExpect(this.hank.buddies.hasDirect(this.bob), false);
  }

  // Tests for the samePerson() method
  void testSamePerson(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.samePerson(this.ann), true);
    t.checkExpect(this.ann.samePerson(this.bob), false);

  }

  // Tests for the countCommonBuddies() and countCommon() methods
  void testCountCommonBuddies(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.ann.countCommonBuddies(this.hank), 0);

    t.checkExpect(this.ann.buddies.countCommon(this.bob.buddies), 0);
    t.checkExpect(this.ann.buddies.countCommon(this.dan.buddies), 1);
    t.checkExpect(this.ann.buddies.countCommon(this.hank.buddies), 0);
  }

  // Tests for hasExtendedBuddy(), hasExtendedBuddyHelper(), and hasExtended()
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

    t.checkExpect(this.ann.hasExtendedBuddyHelper(dan, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(bob, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.hasExtendedBuddyHelper(ed, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(cole, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddyHelper(jan, new MTLoBuddy()), false);
    t.checkExpect(this.jan.hasExtendedBuddyHelper(len, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.hasExtendedBuddyHelper(bob, new MTLoBuddy()), false);
    t.checkExpect(this.hank.hasExtendedBuddyHelper(kim, new MTLoBuddy()), false);

    t.checkExpect(this.ann.buddies.hasExtended(dan, new MTLoBuddy()), true);
    t.checkExpect(this.ann.buddies.hasExtended(bob, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.buddies.hasExtended(ed, new MTLoBuddy()), true);
    t.checkExpect(this.ann.buddies.hasExtended(cole, new MTLoBuddy()), true);
    t.checkExpect(this.ann.buddies.hasExtended(jan, new MTLoBuddy()), false);
    t.checkExpect(this.jan.buddies.hasExtended(len, new MTLoBuddy()), true);
    t.checkExpect(this.gabi.buddies.hasExtended(bob, new MTLoBuddy()), false);
    t.checkExpect(this.hank.buddies.hasExtended(kim, new MTLoBuddy()), false);
  }

  // Tests for partyCount(), partyCountHelper(), and partyCounter()
  void testPartyCount(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.partyCount(), 8);
    t.checkExpect(this.ed.partyCount(), 3);
    t.checkExpect(this.gabi.partyCount(), 3);
    t.checkExpect(this.hank.partyCount(), 1);
    t.checkExpect(this.len.partyCount(), 3);

    t.checkExpect(this.ann.partyCountHelper(new ConsLoBuddy(this.ann, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 8);
    t.checkExpect(this.ed.partyCountHelper(new ConsLoBuddy(this.ed, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
    t.checkExpect(this.gabi.partyCountHelper(new ConsLoBuddy(this.gabi, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
    t.checkExpect(this.hank.partyCountHelper(new ConsLoBuddy(this.hank, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 1);
    t.checkExpect(this.len.partyCountHelper(new ConsLoBuddy(this.len, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);

    t.checkExpect(this.ann.buddies.partyCounter(new ConsLoBuddy(this.ann, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 8);
    t.checkExpect(this.ed.buddies.partyCounter(new ConsLoBuddy(this.ed, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
    t.checkExpect(this.gabi.buddies.partyCounter(new ConsLoBuddy(this.gabi, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
    t.checkExpect(this.hank.buddies.partyCounter(new ConsLoBuddy(this.hank, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 1);
    t.checkExpect(this.len.buddies.partyCounter(new ConsLoBuddy(this.len, new MTLoBuddy()))
        .countUnique(new MTLoBuddy()), 3);
  }

  // Tests for the appendConsLo() method
  void testApendConsLo(Tester t) {
    initBuddies();
    ILoBuddy mt = new MTLoBuddy();
    ILoBuddy cons1 = new ConsLoBuddy(this.ann, new ConsLoBuddy(this.bob, new MTLoBuddy()));
    ILoBuddy cons2 = new ConsLoBuddy(this.cole, new ConsLoBuddy(this.dan, new MTLoBuddy()));

    t.checkExpect(mt.appendConsLo(mt), new MTLoBuddy());
    t.checkExpect(mt.appendConsLo(cons1),
        new ConsLoBuddy(this.ann, new ConsLoBuddy(this.bob, new MTLoBuddy())));
    t.checkExpect(cons1.appendConsLo(cons2), new ConsLoBuddy(this.bob, new ConsLoBuddy(this.ann,
        new ConsLoBuddy(this.cole, new ConsLoBuddy(this.dan, new MTLoBuddy())))));
  }

  // Tests for countUnique()
  void testCountUnique(Tester t) {
    initBuddies();
    t.checkExpect(this.ann.buddies.countUnique(new MTLoBuddy()), 2);
    t.checkExpect(this.hank.buddies.countUnique(new MTLoBuddy()), 0);
  }

  // Tests for maxLikelihood()
  void testMaxLikelihood(Tester t) {
    initBuddies();

    t.checkInexact(this.personA.maxLikelihood(this.personA), 1.0, .001);
    t.checkInexact(this.personA.maxLikelihood(this.personB), .9405, .001);
    t.checkInexact(this.personA.maxLikelihood(this.personC), .855, .001);
    t.checkInexact(this.personE.maxLikelihood(this.personA), 0.0, .001);
    t.checkInexact(this.personA.maxLikelihood(this.personD), .772, .001);
  }

  // Test for findMax()
  void testFindMax(Tester t) {
    initBuddies();

    t.checkExpect(this.personA.buddies.findMax(this.personB, 1.0, this.personA.buddies),
        1.0 * 0.99);
    t.checkExpect(this.personA.buddies.findMax(this.personC, 1.0, this.personB.buddies), 0.9);
    t.checkExpect(this.personE.buddies.findMax(this.personC, 12, this.personC.buddies), 0.0);
  }

  // Test for maxLikelihoodHelper()
  void testMaxLikelihoodHelper(Tester t) {
    initBuddies();

    t.checkInexact(this.personA.maxLikelihoodHelper(this.personB, this.personA.buddies), .9405,
        .001);
    t.checkInexact(this.personA.maxLikelihoodHelper(this.personC, this.personC.buddies), .855,
        .001);
    t.checkExpect(this.personE.maxLikelihoodHelper(this.personA, this.personA.buddies), 0.0);
  }

  // Test for updateScore()
  void testUpdateScore(Tester t) {
    initBuddies();

    t.checkExpect(this.personA.updateScore(100.0), this.personA.hearing * 100);
    t.checkExpect(this.personA.updateScore(0.0), 0.0);
  }
}