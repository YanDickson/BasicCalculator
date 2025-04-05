# Basic Java Calculator App
<img width="401" alt="Screenshot 2025-04-02 at 11 04 08 PM" src="https://github.com/user-attachments/assets/01ab7ce1-4f1d-40a2-8a74-35fc30c4899b" />

This project is a basic calculator application. 
This project serves as a beginner project to get a basic understanding of how to create 
a Java application that has a user interface that users can interact with.

The `Java Swing` library was used to create the user interface. The `ActionEvent` class and `ActionEvent` 
interface were used to handle users clicking calculator buttons. The app does the basic arithmetic functions of a 
calculator - namely, addition, multiplication, subtraction and division - by taking two numbers, performing the 
calculation and displaying the output.

## Built With
- `Java`
- [`Java Swing`](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)

## Getting Started
### Prerequisites
The following are required to get started with this project: 
- [Java](https://www.oracle.com/java/technologies/downloads/) installed
- A Java IDE like [Eclipse]() or [intelliJ]()

## Run the Project
1. Create a fork of this repo.
2. Clone the forked repo:
```git clone https://github.com/{your_username}/BasicCalculator.git```.
3. Launch your Java IDE.
4. Use the IDE to open the project.
5. Use the IDE to run the `Main.java` file located in the `src` folder.
6. The calculator should be launched and ready for use.

## How to Use the App
Follow the below steps once the app is successfully launched.

### Performing arithmetic operations
Arithmetic operations require entering two numbers, specifying the operation and hitting the `=` sign.
1. Click number buttons to enter the first number. 
2. Click one of the available operators: `+`, `-`, `x`, `÷`.
3. Click number buttons to enter the second number.
4. Click the `=` button to generate a result.


### Clearing everything from the screen
The `CA` button is used to clear everything that is seen in the display bar.
Once this button is clicked, the app resets to its initial state.
1. Click number keys, or perform an operation.
2. Click the `CA` button to remove everything from the screen.


### Deleting entered numbers
The `DEL` button deletes the last (right-most) integer from the entered integers.
1. Click number keys to display numbers on screen.
2. Click the `DEL` button as many times as needed.


### Negating a number
The `(-)` button is used to convert the entered number to it's negative form, for example, `5` to `-5`.
1. Click number keys to display numbers on screen.
2. Click the `(-)` button.

## Lessons Learnt

### 1. Displaying precise arithmetic results
#### Problem
In the first version of this project, the numbers inputted to the calculator were converted 
from `string` to `double`. Calculations were then done with this double data type. In some cases, 
the result displayed onscreen was not precise. For example, when multiplying `1.1` by `2.1` the expected result 
was `2.31` but what was displayed was `2.3100000000000005`.

#### Solution
To get a precise arithmetic result, the `BigDecimal` class was used. Two main things were done with this class:
1. the string inputs were converted to `BigDecimal` objects. Example:

   ```
   BigDecimal num1 = new BigDecimal("1.1");
   BigDecimal num1 = new BigDecimal("2.1");
    ```
2. methods from the `BigDecimal` class were used for arithmetic calculations. Example:

    ```BigDecimal result = num1.multiply(num2);```

The `double` data point, while represents floating point numbers, they have limited precision because of their 
binary representations. `BigDecimal` store numbers such that loss of precision is avoided.
