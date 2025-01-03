CREATE TABLE IF NOT EXISTS simulation_configurations (
    id SERIAL PRIMARY KEY,
    totalTicketsForVendor INT,
    totalTicketsForCustomer INT,
    totalVendors INT,
    totalConsumers INT,
    ticketReleaseRate INT,
    customerRetrievalRate INT,
    maxTicketCapacity INT
);

DROP TABLE IF EXISTS simulation_configuration_validation_constraints;
CREATE TABLE IF NOT EXISTS simulation_configuration_validation_constraints (
    -- Used to validate the Simulation Configuration
    -- id : Autogenerated by the DBMS
    -- field_name : Field to be validated
    -- min_value : Minimum value of the field
    -- max_value : Maximum value of the field
    -- description : Messages that will be shown to the user in case of an error
    id SERIAL PRIMARY KEY,
    field_name VARCHAR(255) NOT NULL,
    min_value INT,
    max_value INT,
    description VARCHAR(255)
);
