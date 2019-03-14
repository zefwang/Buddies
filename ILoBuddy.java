// represents a list of Person's buddies
interface ILoBuddy {

  // Changes this person's buddy list to include the given people
  void addMultBuddies(Person target);

  // Determines if that person is a direct friend of this person
  boolean hasDirect(Person that);

  // Finds the number of common buddies two Persons have
  int countCommon(ILoBuddy that);
  
  // Determines if that person is an extended buddy (direct or indirect)
  boolean hasExtended(Person that, ILoBuddy soFar);

  // Returns all the individuals who will be invited to the party
  ILoBuddy partyCounter(ILoBuddy soFar);

  // Puts together two ILoBuddy
  ILoBuddy appendConsLo(ILoBuddy that);

  // Counts all the unique items in an ILo
  int countUnique(ILoBuddy acc);

  // Finds the maximum likelihood
  double findMax(Person that, double score, ILoBuddy soFar);
}
