Feature: Mobiquity

Scenario: Validate Users API
Given I have access to UsersAPI
When I send Get request for the user "Delphine"
Then I should validate StatusCode

