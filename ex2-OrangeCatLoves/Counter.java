// author @ Low Wei Bin Lab 16D
class Counter {

  private boolean availability;
  private int counterID;

  public Counter(int counterID) {
    this.availability = true;
    this.counterID = counterID;
  }

  public String toString() {
    return "(by S" + Integer.toString(this.counterID) + ")";
  }

  public boolean getAvailability() {
    return this.availability;
  }

  public boolean isAvailable() {

    if (this.availability) {
      return true;
    } else {
      return false;
    }
  }

  public void setBusy() {
    this.availability = false;
  }

  public void setAvailable() {
    this.availability = true;
  }

}

