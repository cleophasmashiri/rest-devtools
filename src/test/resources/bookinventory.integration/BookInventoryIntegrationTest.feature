@restApiIntegration
Feature: Example Resource Integration Test

  Scenario: create a book
    Given the web context is set
    Given the db is empty
    Given the isbn gateway is mocked
    When client request POST /api/books with json data:
    """
    {"isbn":null,"title":"my book","author":"me"}
    """
    Then the response code should be 201
    Then the following header should present "Location" with value "http://localhost/api/books/isbn1234"

  Scenario: find by isbn
    Given the web context is set
    Given the db is empty
    Given the following books exist:
    | isbn  |    title              |    author            |
    | n123  | Hamlet                |  William Shakespeare |
    | n124  | Romeo and Juliet      |  William Shakespeare |
    | n125  | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books/n125
    Then the response code should be 200
    Then the result json should be:
    """
    {"isbn":"n125","title":"To Kill a Mockingbird","author":"Harper lee"}
    """

  Scenario: find by author
    Given the web context is set
    Given the db is empty
    Given the following books exist:
      | isbn  |    title              |    author            |
      | n123  | Hamlet                |  William Shakespeare |
      | n124  | Romeo and Juliet      |  William Shakespeare |
      | n125  | To Kill a Mockingbird |  Harper Lee          |
    When client request GET /api/books?author=William%20Shakespeare
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"n123","title":"Hamlet","author":"William Shakespeare"},
     {"isbn":"n124","title":"Romeo and Juliet","author":"William Shakespeare"}]
    """

  Scenario: find by title
    Given the web context is set
    Given the db is empty
    Given the following books exist:
      | isbn  |    title              |    author            |
      | n123  | Hamlet                |  William Shakespeare |
      | n124  | Romeo and Juliet      |  William Shakespeare |
      | n125  | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books?title=Romeo%20and%20Juliet
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"n124","title":"Romeo and Juliet","author":"William Shakespeare"}]
    """

  Scenario: find all
    Given the web context is set
    Given the db is empty
    Given the following books exist:
      | isbn  |    title              |    author            |
      | n123  | Hamlet                |  William Shakespeare |
      | n124  | Romeo and Juliet      |  William Shakespeare |
      | n125  | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books/all
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"n123", "title":"Hamlet","author":"William Shakespeare"},
     {"isbn":"n124", "title":"Romeo and Juliet","author":"William Shakespeare"},
     {"isbn":"n125", "title":"To Kill a Mockingbird","author":"Harper lee"}]
    """