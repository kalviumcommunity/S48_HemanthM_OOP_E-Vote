import java.util.*;

abstract class Person {
    protected String name;

    public Person() {
        this.name = "Unknown";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void castVote(String candidate, String party);
}

// Class for Voter Management <- Added a new class to follow SRP
class VoterManager {
    private Voter[] voters;
    private int votersVoted;

    public VoterManager(Voter[] voters) {
        this.voters = voters;
        this.votersVoted = 0;
    }

    public Voter[] getVoters() {
        return voters;
    }

    public void incrementVotersVoted() {
        votersVoted++;
    }

    public int getVotersVoted() {
        return votersVoted;
    }

    public boolean validateVoter(String name, String voterId) {
        for (Voter voter : voters) {
            if (voter.getName().equals(name) && voter.getVoterId().equals(voterId)) {
                return true;
            }
        }
        return false;
    }

    public Voter getVoter(String name, String voterId) {
        for (Voter voter : voters) {
            if (voter.getName().equals(name) && voter.getVoterId().equals(voterId)) {
                return voter;
            }
        }
        return null;
    }
}

// Class for Candidate Management <- Added a new class to follow SRP
class CandidateManager {
    private Candidate[] candidates;

    public CandidateManager(Candidate[] candidates) {
        this.candidates = candidates;
    }

    public Candidate[] getCandidates() {
        return candidates;
    }

    public void displayCandidates() {
        for (int i = 0; i < candidates.length; i++) {
            System.out.println((i + 1) + ": " + candidates[i].getName() + " (" + candidates[i].getParty() + ")");
        }
    }

    public void displayTotalVotes() {
        for (Candidate candidate : candidates) {
            candidate.displayPartyVotes();
        }
    }
}

class Voter extends Person {

    private String voterId;
    private static int totalVoters = 0;
    private boolean hasVoted;

    public Voter() {
        super("Bob");
        this.voterId = "V002";
        this.hasVoted = false;
        totalVoters++;
    }

    public Voter(String name) {
        super(name);
        this.voterId = "V000";
        this.hasVoted = false;
        totalVoters++;
    }

    public Voter(String name, String voterId) {
        super(name);
        this.voterId = voterId;
        this.hasVoted = false;
        totalVoters++;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public static int getTotalVoters() {
        return totalVoters;
    }

    @Override
    public void castVote(String candidate, String party) {
        System.out.println("Voter ID: " + voterId + ", " + name + " voted for " + candidate + " of " + party);
    }
}

class Candidate extends Person {

    private String party;
    private int voteCount;
    private static int totalVotesCast = 0;

    public Candidate() {
        super("Azad");
        this.party = "Green Party";
    }

    public Candidate(String name) {
        super(name);
        this.party = "Independent";
    }

    public Candidate(String name, String party) {
        super(name);
        this.party = party;
        this.voteCount = 0;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public static int getTotalVotesCast() {
        return totalVotesCast;
    }

    public void receiveVote() {
        voteCount++;
        totalVotesCast++;
    }

    public static void displayTotalVotesCast() {
        System.out.println("Total Votes Cast in Election: " + totalVotesCast);
    }

    public void displayPartyVotes() {
        System.out.println(party + " Party Votes: " + voteCount);
    }

    @Override
    public void castVote(String candidate, String party) {
        System.out.println("Candidates cannot vote in the election.");
    }
}

public class mainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Voter[] voters = new Voter[15];
        voters[0] = new Voter("John", "V001");
        voters[1] = new Voter();
        voters[2] = new Voter("Alice", "V003");
        voters[3] = new Voter("Ravi", "V004");
        voters[4] = new Voter("Sita", "V005");
        voters[5] = new Voter("Mohan", "V006");
        voters[6] = new Voter("Radha", "V007");
        voters[7] = new Voter("Suresh", "V008");
        voters[8] = new Voter("Geeta", "V009");
        voters[9] = new Voter("Vikram", "V010");
        voters[10] = new Voter("Anjali", "V011");
        voters[11] = new Voter("Karan", "V012");
        voters[12] = new Voter("Deepa", "V013");
        voters[13] = new Voter("Ramesh", "V014");
        voters[14] = new Voter("Sunita", "V015");

        Candidate[] candidates = new Candidate[2];
        candidates[0] = new Candidate("Bhagat", "Red Party");
        candidates[1] = new Candidate();

        VoterManager voterManager = new VoterManager(voters); // <- SRP applied
        CandidateManager candidateManager = new CandidateManager(candidates); // <- SRP applied

        while (true) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your Voter ID:");
            String voterId = scanner.nextLine();

            if (!voterManager.validateVoter(name, voterId)) {
                System.out.println("Invalid voter details. Try again.");
                continue;
            }

            Voter voter = voterManager.getVoter(name, voterId);
            if (voter.hasVoted()) {
                System.out.println("You have already voted.");
                continue;
            }

            System.out.println("Enter candidate's unique number:");
            candidateManager.displayCandidates();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > candidates.length) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            Candidate candidate = candidates[choice - 1];
            voter.castVote(candidate.getName(), candidate.getParty());
            candidate.receiveVote();
            voter.setHasVoted(true);
            voterManager.incrementVotersVoted();

            System.out.println("\n1. Exit\n2. Show Total Votes");
            int option = scanner.nextInt();
            if (option == 1) {
                System.out.println("Exiting...");
                scanner.close();
                break;
            } else if (option == 2) {
                candidateManager.displayTotalVotes();
            }
        }
    }
}
