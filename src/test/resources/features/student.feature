#Author: vikash.pandey@fisglobal.com
#Keywords Summary : To demo Cucumber intgration in sample project
Feature: Student Certification
Background: 
    Scenario: Student score decides whether it Passed or Failed
    		Given that the student Vikash is given a task to clear Java certification exam
        When Vikash got 60 marks in exam
        Then Vikash passed Java certification
        
   	    When Vikash got 50 marks in exam
        Then Vikash failed Java certification