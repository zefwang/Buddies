
// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.hasDirect(that);
  }

  // Returns true if this Person is the same as the given
  boolean samePerson(Person that) {
    return this.username.equals(that.username);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return 1 + this.partyCountHelper(new ConsLoBuddy(this, new MTLoBuddy()), this);
  }
  
  int partyCountHelper(ILoBuddy soFar, Person original) {
    return this.buddies.partyCounter(soFar, original);
  }

  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return this.buddies.countCommon(that.buddies);
  }

  // will the given person be invited to a party
  // organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.buddies.hasExtended(that, new MTLoBuddy());
  }

  // Helper to determine the given person will be invited to this
  // person's party.
  boolean hasExtendedBuddyHelper(Person that, ILoBuddy soFar) {
    return this.buddies.hasExtended(that, soFar);
  }

  // Changes this person's buddy list to include the given person
  void addBuddy(Person buddy) {
    this.buddies = new ConsLoBuddy(buddy, this.buddies);
  }

  // Changes this person's buddy list to include the given people
  void addManyBuddies(ILoBuddy newBuds) {
    newBuds.addMultBuddies(this);
  }
}
