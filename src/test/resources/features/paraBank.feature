Feature: Verify that user is able make different transactions between different accounts

  Scenario:
    Given user go to the url "https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC"
    Then user enters login "Hello2" and password "123456"
    And user press logIn button
    And user direct to Transfer Funds Service department


  Scenario Outline: user should be able to transfer different positive amounts of funds from account A to account B
    Given choose "<amount>"
    Then choose from "37209" and To "37320"
    Then click on button transfer
    Then Verify the message of transfering the choosen amount
    And come back to transfer funds service

    Examples:
      | amount |
      | 100    |
      | 100000 |


  Scenario Outline: user should not be able to transfer small or negative amounts of funds from account A to account B
    Given choose negative "<amount2>"
    Then choose from another accounts "37320" and To "37209"
    Then click on button transfer again
    Then Verify the error message
    And go back on the transfer page

    Examples:
      | amount2 |
      | -100    |
      | 0       |
      | 0.5     |



  Scenario: user should not be able to transfer any amount from the same account "From" and "To"
    Given choose amount "250"
    Then choose from account "37431" to "37431" and click on transfer button
    And Verify the error message that is user have to choose different accounts



  Scenario: user should be able to see the decrising of one account and increasing another account after transferring funds
    Given choose new fund "1000"
    Then choose accounts "37875" to "37764" and click on transfer button
    Then go to the accountOverview service
    And Verify the increasing and decrising amounts on the accounts respectively


#  Scenario: how to crash the system of the user account totally
#    Given go on Transfer service
#    When choose very small amount for transfering "0.005"
#    Then choose different accounts "29217" and To "29106"
#    Then click on button to make a transfer
#    And see the message ERROR for crashing the system



  Scenario: user should not be able to click on transfer button without choosing any amount for funds
    Given choose from last accounts "37431" and To "37209"
    Then click on button transfer with keeping amount field empty
    And Verify the error message for user to choose the amount for his transfer needs


