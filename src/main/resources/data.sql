-- ToDo: This is only for development stage, remove this file at the deployment stage
-- This SQL File will be executed at the startup of the application
-- It will first truncate the appropriate tables and insert necessary data into them
-- This is done to make sure every application startup has new fresh data in the database

-- Truncate the tables
TRUNCATE TABLE simulation_configuration_validation_constraints CASCADE;
TRUNCATE TABLE simulation_configurations CASCADE;

-- Insert data into the configs table
INSERT INTO simulation_configurations (totalTicketsForCustomer, totalTicketsForVendor, totalVendors, totalConsumers, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity)
VALUES
    (10, 10, 3, 3, 2, 2, 100),
    (30, 10, 1, 3, 2, 1, 100),
    (10, 100, 10, 1, 5, 1, 100);


-- Insert data into the validation_constraints table
INSERT INTO simulation_configuration_validation_constraints (field_name, min_value, max_value, description)
VALUES
    ('totalTicketsForCustomer', 1, 1000, 'Tickets per customer validation.'),
    ('totalTicketsForVendor', 1, 1000, 'Tickets per vendor validation.'),
    ('totalVendors', 1, 100, 'Vendors count validation.'),
    ('totalConsumers', 1, 100, 'Customers count validation.'),
    ('ticketReleaseRate', 1, 100, 'Ticket release rate for vendor'),
    ('customerRetrievalRate', 1, 100, 'Ticket retrieval rate for customer'),
    ('maxTicketCapacity', 1, 1000, 'Maximum Ticket Capacity of the Ticket Pool');
