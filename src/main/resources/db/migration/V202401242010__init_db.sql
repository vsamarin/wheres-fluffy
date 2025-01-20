CREATE TABLE public.pet
(
    id          BIGSERIAL PRIMARY KEY,
    name        varchar(50)  NOT NULL,
    species     varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    status      varchar(10)  NOT NULL
);

CREATE SEQUENCE pet_id_seq
    MINVALUE 1
    MAXVALUE 1000
    INCREMENT BY 1
    START WITH 3 NOCACHE
    NOCYCLE;

ALTER TABLE public.pet
    ADD CONSTRAINT pet_uq UNIQUE (name);

INSERT INTO public.pet(name, species, description, status)
VALUES ('Майло', 'Собака, Джек-Рассел-Терьер',
        'Последний раз видели с человеком в желтом костюме и широкополой шляпе в странной зеленой маске.', 'MISSING'),
       ('Бетховен', 'Собака породы Сенбернар', 'Последний раз был замечен рядом с местной Филармонией', 'MISSING');
