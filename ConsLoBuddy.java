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

  // Determines if that person is a direct friend of this person
  public boolean hasDirect(Person that) {
    return this.first.samePerson(that) || this.rest.hasDirect(that);
  }

  // Finds the number of common buddies two Persons have
  public int countCommon(ILoBuddy that) {
    if (that.hasPerson(this.first)) {
      return 1 + this.rest.countCommon(that);
    }
    else {
      return this.rest.countCommon(that);
    }
  }

  // Determines if a list has that person
  public boolean hasPerson(Person that) {
    return this.first.samePerson(that) || this.rest.hasPerson(that);
  }

  // Determines if that person is an extended buddy (direct or indirect)
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

  // Returns all the individuals who will be invited to the party
  public ILoBuddy partyCounter(ILoBuddy soFar) {
    if (!(soFar.hasPerson(this.first))) {
      return this.rest.partyCounter(new ConsLoBuddy(this.first, soFar))
          .appendConsLo(this.first.partyCountHelper(new ConsLoBuddy(this.first, soFar)));
    }
    else {
      return this.rest.partyCounter(soFar);
    }
  }

  // Puts together two ILoBuddy
  public ILoBuddy appendConsLo(ILoBuddy that) {
    return this.rest.appendConsLo(new ConsLoBuddy(this.first, that));
  }

  // Counts all the unique items in a list
  public int countUnique(ILoBuddy acc) {
    if (!(acc.hasPerson(this.first))) {
      return 1 + this.rest.countUnique(new ConsLoBuddy(this.first, acc));
    }
    else {
      return this.rest.countUnique(acc);
    }
  }

  // Finds the maximum likelihood
  public double findMax(Person that, double score, ILoBuddy soFar) {
    // The first person is that person
    if (this.first.samePerson(that)) {
      return score * that.hearing;
    }
    // Haven't visited this person and can reach that person
    else if (!(soFar.hasPerson(this.first)) && this.first.hasExtendedBuddy(that)) {
      soFar = new ConsLoBuddy(this.first, soFar);
      return Math.max(this.first.updateScore(score) * this.first.maxLikelihoodHelper(that, soFar),
          this.rest.findMax(that, score, soFar));
    }
    // Have already visited this.first person
    else {
      return this.rest.findMax(that, score, soFar);
    }
  }
}
