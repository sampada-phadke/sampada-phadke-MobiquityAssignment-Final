Feature: Mobiquity

Scenario: Validate Users API
Given I have access to UsersAPI
When I send Get request for the user "Delphine"
Then I should validate StatusCode



Scenario: Search the Posts by userID
Given I have access to Posts API
When I send request to fetch Posts written by the user
Then I should validate StatusCode

Scenario: Validate the Comments API by PostID
Given I have access to Comments API
When I send request to retrieve Comments written by the user
Then I should validate StatusCode
And Validate emailID in comments section
