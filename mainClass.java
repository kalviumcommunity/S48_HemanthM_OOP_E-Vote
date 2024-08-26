import java.util.*;

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
        // Sample voters
        Voter[] voters = {
            new Voter("John", "V001"),
            new Voter("Bob", "V002"),
            new Voter("Alice", "V003"),
            new Voter("Ravi", "V004"),
            new Voter("Sita", "V005"),
            new Voter("Mohan", "V006"),
            new Voter("Radha", "V007"),
            new Voter("Suresh", "V008"),
            new Voter("Geeta", "V009"),
            new Voter("Vikram", "V010"),
            new Voter("Anjali", "V011"),
            new Voter("Karan", "V012"),
            new Voter("Deepa", "V013"),
            new Voter("Ramesh", "V014"),
            new Voter("Sunita", "V015")
        };

        // Sample candidates
        Candidate candidate1 = new Candidate("Bhagat", "Red Party");
        Candidate candidate2 = new Candidate("Azad", "Green Party");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String inputName = scanner.nextLine();
        System.out.println("Enter your Voter ID:");
        String inputVoterId = scanner.nextLine();

        boolean voterFound = false;
        for (Voter voter : voters) {
            if (voter.name.equals(inputName) && voter.voterId.equals(inputVoterId)) {
                voterFound = true;
                System.out.println("Voter verified.");

                System.out.println("Enter candidate's unique number (1 for Bhagat, 2 for Azad):");
                int candidateChoice = scanner.nextInt();

                if (candidateChoice == 1) {
                    voter.castVote(candidate1.name, candidate1.party);
                    candidate1.receiveVote();
                } else if (candidateChoice == 2) {
                    voter.castVote(candidate2.name, candidate2.party);
                    candidate2.receiveVote();
                } else {
                    System.out.println("Invalid candidate choice.");
                }
                break;
            }
        }

        if (!voterFound) {
            System.out.println("Voter not found or incorrect details provided.");
        }
        
        candidate1.displayInfo();
        candidate2.displayInfo();

        scanner.close();
    }
}
