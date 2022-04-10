CREATE TABLE connection_config_overrides (
    institution_id uuid NOT NULL,
    operation VARCHAR(36) NOT NULL,
    base_url VARCHAR(256) NOT NULL,
    routing_number VARCHAR(9) NOT NULL,
    jxchange_env_type VARCHAR(16) NOT NULL default 'PROD',
    created_at timestamp(3) with time zone NOT NULL,
    updated_at timestamp(3) with time zone NOT NULL,
    deleted_at timestamp(3) with time zone NOT NULL,
    PRIMARY KEY(institution_id, operation)
);