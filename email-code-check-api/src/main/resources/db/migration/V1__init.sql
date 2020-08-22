CREATE TABLE email_verifications (
    verification_id SERIAL PRIMARY KEY,
    email CHARACTER VARYING(128) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    verification_code CHARACTER VARYING(16) NOT NULL,
    verification_status CHARACTER VARYING(16),
    message CHARACTER VARYING(256)
)