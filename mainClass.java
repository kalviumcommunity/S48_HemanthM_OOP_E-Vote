import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        Voter[] voters = new Voter[5];
        voters[0] = new Voter("John", "V001");
        voters[1] = new Voter("Bob", "V002");
        voters[2] = new Voter("Alice", "V003");
        voters[3] = new Voter("Ravi", "V004");
        voters[4] = new Voter("Sita", "V005");

        Candidate[] candidates = new Candidate[2];
        candidates[0] = new Candidate("Bhagat", "Red Party");
        candidates[1] = new Candidate("Azad", "Green Party");

        System.out.println("Enter your name:");
        String inputName = scanner.nextLine();
        System.out.println("Enter your Voter ID:");
        String inputVoterId = scanner.nextLine();

        boolean voterFound = false;
        for (Voter voter : voters) {
            if (voter.name.equals(inputName) && voter.voterId.equals(inputVoterId)) {
                voterFound = true;
                System.out.println("Voter verified.");

                System.out.println("Enter candidate's unique number to vote:");
                for (int i = 0; i < candidates.length; i++) {
                    System.out.println((i + 1) + ": " + candidates[i].name + " (" + candidates[i].party + ")");
                }

                int candidateChoice = scanner.nextInt();

                if (candidateChoice >= 1 && candidateChoice <= candidates.length) {
                    voter.castVote(candidates[candidateChoice - 1].name, candidates[candidateChoice - 1].party);
                    candidates[candidateChoice - 1].receiveVote();
                } else {
                    System.out.println("Invalid candidate choice.");
                }
                break;
            }
        }

        if (!voterFound) {
            System.out.println("Voter not found or incorrect details provided.");
        }

        System.out.println("\nElection Results:");
        for (Candidate candidate : candidates) {
            candidate.displayInfo();
        }

        scanner.close();
    }
}
