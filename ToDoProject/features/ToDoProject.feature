Feature: Sample API Test

Scenario: To Test toDO List with GET method
	Given I have End Point of toDo List API
	When I GET the id '200' details 
	And Validate the GET status code has '200'
	And Validate the time taken for GET request

Scenario: To Test toDO List with POST method
	When I POST the new userid '201', id '201', title "API Test", completed "true" in to the list
	And Validate the Post status code has '201'
	Then Validate the time taken for POST request


