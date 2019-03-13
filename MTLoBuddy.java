
// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
  MTLoBuddy() {
  }

  // Changes this person's buddy list to include the given people (none)
  public void addMultBuddies(Person target) {
    // This is a void method
  }

  // Determines if that person is a direct friend of this person
  public boolean hasDirect(Person that) {
    return false;
  }

  // Finds the number of common buddies two Persons have
  public int countCommon(ILoBuddy that) {
    return 0;
  }

  // Determines if an empty list has that person
  public boolean hasPerson(Person that) {
    return false;
  }

  // Determines if that person is an extended buddy (direct or indirect)
  public boolean hasExtended(Person that, ILoBuddy soFar) {
    return false;
  }

  // Returns all the individuals who will be invited to the party
  public ILoBuddy partyCounter(ILoBuddy soFar) {
    return soFar;
  }

  // Puts together two ILoBuddy
  public ILoBuddy appendConsLo(ILoBuddy that) {
    return that;
  }

  // Counts all the unique items in an empty list
  public int countUnique(ILoBuddy acc) {
    return 0;
  }

  // Finds the maximum likelihood (0.0)
  public double findMax(Person that, double score) {
    return 0.0;
  }
}
