
// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
  MTLoBuddy() {
  }

  // Changes this person's buddy list to include the given people (none)
  public void addMultBuddies(Person target) {
  }
  
  public boolean hasDirect(Person that) {
    return false;
  }
  
  public int countCommon(ILoBuddy that) {
    return 0;
  }
  
  public boolean hasPerson(Person that) {
    return false;
  }
  
  public boolean hasExtended(Person that, ILoBuddy soFar) {
    return false;
  }
  
  public ILoBuddy partyCounter(ILoBuddy soFar) {
    return soFar;
  }
  
  public ILoBuddy appendConsLo(ILoBuddy that) {
    return that;
  }

  public int countUnique(ILoBuddy acc) {
    return 0;
  }
}
