// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  // Changes this person's buddy list to include the given people
  public void addMultBuddies(Person target) {
    target.addBuddy(this.first);
    target.addManyBuddies(this.rest);
  }

  public boolean hasDirect(Person that) {
    return this.first.samePerson(that) || this.rest.hasDirect(that);
  }

  public int countCommon(ILoBuddy that) {
    if (that.hasPerson(this.first)) {
      return 1 + this.rest.countCommon(that);
    }
    else {
      return this.rest.countCommon(that);
    }
  }

  public boolean hasPerson(Person that) {
    return this.first.samePerson(that) || this.rest.hasPerson(that);
  }

  public boolean hasExtended(Person that, ILoBuddy soFar) {
    if (soFar.hasPerson(this.first)) {
      return this.hasPerson(that) || this.rest.hasExtended(that, soFar);
    }
    else {
      return this.hasPerson(that)
          || this.first.hasExtendedBuddyHelper(that, new ConsLoBuddy(this.first, soFar))
          || this.rest.hasExtended(that, new ConsLoBuddy(this.first, soFar));
    }
  }
}
