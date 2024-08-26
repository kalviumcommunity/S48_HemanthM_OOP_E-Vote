// Voter class
class Voter {
    String name;
    String voterId;

    Voter(String voterName, String voterIdNumber) {
        name = voterName;
        voterId = voterIdNumber;
    }

    void castVote(String candidate, String party) {
        System.out.println("Voter ID: " + voterId + ", " + name + " voted for " + candidate + " of " + party);
    }
}

// Candidate class
class Candidate {
    String candidateName;
    String partyName;
    int voteCount;

    Candidate(String candidateNameInput, String partyNameInput) {
        candidateName = candidateNameInput;
        partyName = partyNameInput;
        voteCount = 0;
    }

    void displayInfo() {
        System.out.println("Candidate: " + candidateName + ", Party: " + partyName + ", Votes Received: " + voteCount);
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

        voter1.castVote(candidate1.candidateName, candidate1.partyName);
        candidate1.receiveVote();

        voter2.castVote(candidate2.candidateName, candidate2.partyName);
        candidate2.receiveVote();

        candidate1.displayInfo();
        candidate2.displayInfo();
    }
}
