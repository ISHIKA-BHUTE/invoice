
import React, { useState } from 'react';
import {
  Table,
  Button,
  Modal,
  Form,
  FormGroup,
  Label,
  Input,
  ModalHeader,
  ModalBody,
  ModalFooter,
} from 'reactstrap';
// import * as XLSX from 'xlsx';

const Customer = () => {
  const [customers, setCustomers] = useState([]);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [newCustomer, setNewCustomer] = useState({
    customerName: '',
    customerEmail: '',
    address: '',
    city: '',
    country: '',
    customerPhoneNumber: '',
  });

  const addCustomer = () => {
    setShowAddModal(true);
  };
  
  const saveCustomer = () => {
    const newCustomerId = customers.length + 1;
    const newCustomerWithId = { ...newCustomer, id: newCustomerId };
    setCustomers([...customers, newCustomerWithId]);
    resetForm();
  };
  // const saveCustomer = () => {
  //   const newCustomerId = customers.length + 1;
  //   const newCustomerWithId = { ...newCustomer, id: newCustomerId };
  //   setCustomers((prevCustomers) => [...prevCustomers, newCustomerWithId]);
  //   resetForm();
  // };
  

  const editCustomer = (customer) => {
    setSelectedCustomer(customer);
    setShowEditModal(true);
  };

  const saveEditedCustomer = () => {
    setCustomers((prevCustomers) =>
      prevCustomers.map((customer) =>
        customer.id === selectedCustomer.id ? { ...selectedCustomer } : customer
      )
    );
    resetForm();
  };

  const deleteCustomer = (customer) => {
    setCustomers((prevCustomers) =>
      prevCustomers.filter((c) => c.id !== customer.id)
    );
  };

  const cancelAction = () => {
    resetForm();
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCustomer((prevCustomer) => ({ ...prevCustomer, [name]: value }));
  };

  const resetForm = () => {
    setShowAddModal(false);
    setShowEditModal(false);
    setSelectedCustomer(null);
    setNewCustomer({
      customerName: '',
      customerEmail: '',
      address: '',
      city: '',
      country: '',
      customerPhoneNumber: '',
    });
  };

  const handleFileUpload = async (e) => {
    const file = e.target.files[0];
    const data = new FormData();
    data.append('file', file);
  
    try {
      const response = await fetch('http://localhost:8080/customer/upload/excel', {
        method: 'POST',
        body: data,
      });
  
      if (!response.ok) {
        throw new Error('Error:', response.status);
      }
  
      const responseData = await response.json();
      setCustomers(prevCustomers => [...prevCustomers, ...responseData]);
    } catch (error) {
      console.error('Error:', error);
    }
  };
 

  return (
    <div style={{ padding: '10% 5%' }}>
      <h1>CUSTOMERS</h1>
      <input
        id="fileInput"
        type="file"
        accept=".xlsx, .xls"
        style={{ display: 'none' }}
        onChange={handleFileUpload}
      />
      <Button
        color="primary"
        size="sm"
        onClick={() => document.getElementById('fileInput').click()}
      >
        Upload
      </Button>
      <Button
        color="primary"
        size="sm"
        onClick={addCustomer}
        style={{ marginBottom: '10px', marginLeft: 'auto', display: 'block' }}
      >
        Add Customer
      </Button>
      <Table striped bordered hover style={{ backgroundColor: '#f2f2f2' }}>
        <thead>
          <tr>
            <th>Customer Name</th>
            <th>Customer Email</th>
            <th>Address</th>
            <th>City</th>
            <th>Country</th>
            <th>Phone Number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((customer) => (
            <tr key={customer.id}>
              <td>{customer.customerName}</td>
              <td>{customer.customerEmail}</td>
              <td>{customer.address}</td>
              <td>{customer.city}</td>
              <td>{customer.country}</td>
              <td>{customer.customerPhoneNumber}</td>
              <td>
                <Button
                  color="secondary"
                  size="sm"
                  onClick={() => editCustomer(customer)}
                >
                  Edit
                </Button>{' '}
                <Button
                  color="dark"
                  size="sm"
                  onClick={() => deleteCustomer(customer)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal isOpen={showAddModal} toggle={cancelAction}>
        <ModalHeader toggle={cancelAction}>Add Customer</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="customerName">Customer Name</Label>
              <Input
                type="text"
                placeholder="Enter customer name"
                name="customerName"
                value={newCustomer.customerName}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="customerEmail">Customer Email</Label>
              <Input
                type="email"
                placeholder="Enter customer email"
                name="customerEmail"
                value={newCustomer.customerEmail}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="address">Address</Label>
              <Input
                type="text"
                placeholder="Enter address"
                name="address"
                value={newCustomer.address}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="city">City</Label>
              <Input
                type="text"
                placeholder="Enter city"
                name="city"
                value={newCustomer.city}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="country">Country</Label>
              <Input
                type="text"
                placeholder="Enter country"
                name="country"
                value={newCustomer.country}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="customerPhoneNumber">Phone Number</Label>
              <Input
                type="text"
                placeholder="Enter phone number"
                name="customerPhoneNumber"
                value={newCustomer.customerPhoneNumber}
                onChange={handleInputChange}
              />
            </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={cancelAction}>
            Cancel
          </Button>
          <Button color="primary" onClick={saveCustomer}>
            Save
          </Button>
        </ModalFooter>
      </Modal>

      <Modal isOpen={showEditModal} toggle={cancelAction}>
        <ModalHeader toggle={cancelAction}>Edit Customer</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="editCustomerName">Customer Name</Label>
              <Input
                type="text"
                placeholder="Enter customer name"
                name="customerName"
                value={selectedCustomer ? selectedCustomer.customerName : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    customerName: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editCustomerEmail">Customer Email</Label>
              <Input
                type="email"
                placeholder="Enter customer email"
                name="customerEmail"
                value={selectedCustomer ? selectedCustomer.customerEmail : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    customerEmail: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editAddress">Address</Label>
              <Input
                type="text"
                placeholder="Enter address"
                name="address"
                value={selectedCustomer ? selectedCustomer.address : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    address: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editCity">City</Label>
              <Input
                type="text"
                placeholder="Enter city"
                name="city"
                value={selectedCustomer ? selectedCustomer.city : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    city: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editCountry">Country</Label>
              <Input
                type="text"
                placeholder="Enter country"
                name="country"
                value={selectedCustomer ? selectedCustomer.country : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    country: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editCustomerPhoneNumber">Phone Number</Label>
              <Input
                type="text"
                placeholder="Enter phone number"
                name="customerPhoneNumber"
                value={selectedCustomer ? selectedCustomer.customerPhoneNumber : ''}
                onChange={(e) =>
                  setSelectedCustomer((prevCustomer) => ({
                    ...prevCustomer,
                    customerPhoneNumber: e.target.value,
                  }))
                }
              />
            </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={cancelAction}>
            Cancel
          </Button>
          <Button color="primary" onClick={saveEditedCustomer}>
            Save
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default Customer;
