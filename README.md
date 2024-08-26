# S48_HemanthM_OOP_E-Vote

## Introduction
The **e-Vote** project is an electronic voting system developed using Java. It is a terminal-based application that allows users to vote for candidates in a secure and efficient manner. The system ensures the integrity of the voting process by encapsulating sensitive data and handling errors gracefully.

## Features
- **Voter Registration:** Users can register as voters with age validation.
- **Candidate Registration:** Candidates can be registered with their respective parties.
- **Voting Process:** Registered voters can cast their vote for registered candidates.
- **Vote Counting:** The system accurately counts votes and declares the result.
- **Error Handling:** The system manages invalid inputs and system errors gracefully.

## Concepts Used
1. **Objects and Classes**: Core entities like `Voter` and `Candidate` are modeled as classes with specific attributes and behaviors.
2. **Encapsulation**: Voter data and ballot information are protected through encapsulation, ensuring data integrity.
3. **Abstraction**: The project abstracts complex voting logic, making it easier to manage and extend.
4. **Inheritance**: Potential to reuse code across different user roles (e.g., Admin, Voter).
5. **Polymorphism**: Handling of various actions (e.g., voting, registering) through a common interface.
6. **Exception Handling**: System gracefully handles errors such as invalid inputs or system failures.
