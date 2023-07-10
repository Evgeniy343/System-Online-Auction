import React from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";
import Select from "react-select";

function SectionAuction1(props) {

    const optionsCategory = [
        { id: 1, title: 'AUTO_AND_TRANSPORT' },
        { id: 2, title: 'ELECTRONICS' },
        { id: 3, title: 'HOBBIES_SPORTS_AND_TOURISM' },
        { id: 4, title: 'COMPUTER_TECHNOLOGY' },
    ]


    return (
        <Container>
            <Row>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formProductName">
                        <FormLabel>Product name</FormLabel>
                        <Controller
                            control={props.control}
                            name="product.name"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter product name"
                                        {...field}
                                        type={"text"}
                                        label='product.name'
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                    </FormGroup>
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicCategory">
                        <FormLabel>Category</FormLabel>
                        <Controller
                            control={props.control}
                            defaultValue={""}
                            name="product.category"
                            render={({ field }) => {
                                console.log(field)
                                const { onChange, value, name, ref } = field
                                return (
                                    <Select
                                        name={name}
                                        inputRef={ref}
                                        options={optionsCategory}
                                        value={optionsCategory.find((c) => c.id === value)}
                                        onChange={(val) => {
                                            onChange(val)
                                        }}
                                        getOptionValue={(option) => option.id}
                                        getOptionLabel={(option) => option.title}
                                        className="basic-multi-select"
                                        classNamePrefix="select"
                                    />
                                )
                            }}
                        />
                    </FormGroup>
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicDescription">
                        <FormLabel>Description</FormLabel>
                        <Controller
                            control={props.control}
                            name="product.description"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        {...field}
                                        type={"textarea"}
                                        label='product.description'
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                    </FormGroup>
                </Col>
            </Row>
        </Container>
    );
}

export default SectionAuction1;