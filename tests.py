import unittest
import requests
from datetime import time

BASE_URL = "http://localhost:8080/events"

class EventSystemTest(unittest.TestCase):
    def setUp(self):
        # Sample event data
        self.sample_event = {
            "configId": "config123",
            "vendorId": "vendor456",
            "eventId": "event789",
            "eventName": "Test Event",
            "eventDescription": "This is a test event",
            "startDateTime": "2024-11-01T12:00:00",
            "endDateTime": "2024-11-01T14:00:00",
            "location": "Sample Location"
        }


    def test_create_event(self):
        # Test creating an event
        response = requests.post(BASE_URL, json=self.sample_event)
        if response.status_code != 201:
            print("Error response:", response.json())  # Print error details if status is not 201
        self.assertEqual(response.status_code, 201, "Event creation failed")
        print("Event created successfully.")


    def test_get_all_events(self):
        # Test retrieving all events
        response = requests.get(BASE_URL + "/")
        self.assertEqual(response.status_code, 200, "Failed to retrieve events")
        events = response.json()
        self.assertIsInstance(events, list, "Events response should be a list")
        print("Retrieved events successfully:", events)

    def test_find_event_by_id(self):
        # Test retrieving a specific event by ID (assuming it was created in previous test)
        response = requests.get(f"{BASE_URL}/{self.sample_event['eventId']}")
        if response.status_code == 200:
            event = response.json()
            self.assertEqual(event['eventId'], self.sample_event['eventId'], "Retrieved event ID does not match")
            print("Retrieved event by ID successfully:", event)
        elif response.status_code == 404:
            print("Event not found for ID:", self.sample_event['eventId'])
        else:
            self.fail("Unexpected status code: " + str(response.status_code))

if __name__ == "__main__":
    unittest.main()

