// Voter class
class Voter {
    String name;
    String voterId;

    Voter(String name, String voterId) {
        this.name = name;
        this.voterId = voterId;
    }

    void castVote(String candidate, String party) {
        System.out.println("Voter ID: " + voterId + ", " + name + " voted for " + candidate + " of " + party);
    }
}

// Candidate class
class Candidate {
    String name;
    String party;
    int voteCount;

    Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.voteCount = 0;
    }

    void displayInfo() {
        System.out.println("Candidate: " + name + ", Party: " + party + ", Votes Received: " + voteCount);
    }

    void receiveVote() {
        voteCount++;
    }
}

public class mainClass {
    public static void main(String[] args) {
        Voter voter1 = new Voter("John", "V001");
        Voter voter2 = new Voter("Bob", "V002");

        Candidate candidate1 = new Candidate("Bhagat", "Red Party");
        Candidate candidate2 = new Candidate("Azad", "Green Party");

        voter1.castVote(candidate1.name, candidate1.party);
        candidate1.receiveVote();

        voter2.castVote(candidate2.name, candidate2.party);
        candidate2.receiveVote();

        candidate1.displayInfo();
        candidate2.displayInfo();
    }
}
