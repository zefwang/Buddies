
// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;
  double diction;
  double hearing;

  // Constructor that takes just the name
  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    this.diction = 0.0;
    this.hearing = 0.0;
  }

  // Constructor that takes the name, diction, and hearing levels.
  Person(String username, double diction, double hearing) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    this.diction = diction;
    this.hearing = hearing;
  }

  // Changes this person's buddy list to include the given person
  void addBuddy(Person buddy) {
    if (!this.buddies.hasDirect(buddy)) {
      this.buddies = new ConsLoBuddy(buddy, this.buddies);
    }
  }

  // Changes this person's buddy list to include the given people
  void addManyBuddies(ILoBuddy newBuds) {
    newBuds.addMultBuddies(this);
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.hasDirect(that);
  }

  // Returns true if this Person is the same as the given Person
  boolean samePerson(Person that) {
    return this.username.equals(that.username);
  }

  // returns the number of people that are direct buddies
  // of both this Person and that (given) Person
  int countCommonBuddies(Person that) {
    return this.buddies.countCommon(that.buddies);
  }

  // will the given person be invited to a party
  // organized by this person through direct or indirect buddies?
  boolean hasExtendedBuddy(Person that) {
    return this.hasExtendedBuddyHelper(that, new MTLoBuddy());
  }

  // Helper to determine the given person will be invited to this
  // person's party.
  boolean hasExtendedBuddyHelper(Person that, ILoBuddy soFar) {
    return this.buddies.hasExtended(that, soFar);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return this.partyCountHelper(new ConsLoBuddy(this, new MTLoBuddy()))
        .countUnique(new MTLoBuddy());
  }

  // Returns the individuals that will be invited to this person's party.
  ILoBuddy partyCountHelper(ILoBuddy soFar) {
    return this.buddies.partyCounter(soFar);
  }

  // Computes the maximum likelihood that this Person can
  // convey a message correctly to that (given) Person
  double maxLikelihood(Person that) {
    if (this.samePerson(that)) {
      return 1.0;
    }
    return this.maxLikelihoodHelper(that, new ConsLoBuddy(this, new MTLoBuddy()));
  }

  // A helper to compute the maximum likelihood of a message
  // being conveyed from this Person to that Person
  double maxLikelihoodHelper(Person that, ILoBuddy soFar) {
    return this.buddies.findMax(that, this.diction, soFar);
  }

  // Updates the score by multiplying the current score by this person's hearing.
  double updateScore(double score) {
    return this.hearing * score;
  }
}
