
// represents a list of Person's buddies
interface ILoBuddy {
  
  // Changes this person's buddy list to include the given people
  void addMultBuddies(Person target);
  
  boolean hasDirect(Person that);
  
  int countCommon(ILoBuddy that);
  
  boolean hasPerson(Person that);
  
  boolean hasExtended(Person that, ILoBuddy soFar);
    
  int partyCounter(ILoBuddy soFar, Person original);
}
