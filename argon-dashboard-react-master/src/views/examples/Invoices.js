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

const Invoices = () => {
  const [services, setServices] = useState([]);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedService, setSelectedService] = useState(null);
  const [tableData, setTableData] = useState([]);

  const [newService, setNewService] = useState({
    productServiceId:0,
    rate: 0,
    workingHours: 0,
    billingStartDate: '',
    invoiceDate: '',
    billingEndDate:'',
    invoiceNo:0,
    customer:'',
  });

  const addService = () => {
    setShowAddModal(true);
  };

  const saveService = () => {
    const newServiceId = services.length + 1;
    const newServiceWithId = { ...newService, id: newServiceId };
    setServices([...services, newServiceWithId]);
    resetForm();
  };

  const editService = (service) => {
    setSelectedService(service);
    setShowEditModal(true);
  };

  const saveEditedService = () => {
    setServices((prevServices) =>
      prevServices.map((service) =>
        service.id === selectedService.id ? { ...selectedService } : service
      )
    );
    resetForm();
  };

  const deleteService = (service) => {
    setServices((prevServices) =>
      prevServices.filter((s) => s.id !== service.id)
    );
  };

  const cancelAction = () => {
    resetForm();
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewService((prevService) => ({ ...prevService, [name]: value }));
  };

  const resetForm = () => {
    setShowAddModal(false);
    setShowEditModal(false);
    setSelectedService(null);
    setNewService({ serviceName: '', price: 0, description: '' });
    setTableData([]);
  };
//   const addRow = () => {
//     setTableData((prevData) => [...prevData, newService]);
//     setNewService({ serviceName: '', price: '', description: '' });
//   };
const addRow = () => {
    const newRow = {
        productServiceId:0,
    rate: 0,
    workingHours: 0,
    };
    setTableData(prevData => [...prevData, newRow]);
  };
  const handleTableRowChange = (e, index, field) => {
    const { value } = e.target;
    setTableData((prevData) => {
      const updatedData = [...prevData];
      updatedData[index][field] = value;
      return updatedData;
    });
  };
    

  return (
    <div style={{ padding: '10% 5%' }}>
      <h1>INVOICES</h1>
      <Button
        color="success"
        size="sm"
        onClick={addService}
        style={{ marginBottom: '7px', marginLeft: 'auto', display: 'block' }}
      >
        Create Invoice
      </Button>

      <Table striped bordered hover style={{ backgroundColor: '#f2f2f2' }}>
        <thead>
          <tr>
            <th>Invoice Date</th>
            <th>Invoice NO.</th>
            <th>Customer</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id}>
              <td>{service.invoiceDate}</td>
              <td>{service.invoiceNo}</td>
              <td>{service.customer}</td>
              <td>
                <Button
                  color="secondary"
                  size="sm"
                  onClick={() => editService(service)}
                >
                  Review
                </Button>{' '}
                <Button
                  color="dark"
                  size="sm"
                  onClick={() => deleteService(service)}
                >
                  Download
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal isOpen={showAddModal} toggle={cancelAction}>
        <ModalHeader toggle={cancelAction}>Create Invoice</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="customer">Customer</Label>
              <Input
                type="text"
                placeholder="Enter customer name"
                name="customer"
                value={newService.customer}
                onChange={handleInputChange}
              />
            </FormGroup>
           
            <FormGroup>
              <Label for="billingStartDate">Billing-Start-Date</Label>
              <Input
                type="date"
                placeholder="Enter billingStartDate"
                name="billingStartDate"
                value={newService.billingStartDate}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for=" billingEndDate"> Billing-End-Date</Label>
              <Input
                type="date"
                placeholder="Enter  billingEndDate"
                name=" billingEndDate"
                value={newService.billingEndDate}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
          <Label for="table">LineItems</Label>
          <Table striped bordered>
            <thead>
              <tr>
                <th>Product/Service Id</th>
                <th>Rate</th>
                <th>Working Hours</th>
              </tr>
            </thead>
            {/* <tbody>
              {tableData.map((row, index) => (
                <tr key={index}>
                  <td>{row.serviceName}</td>
                  <td>{row.price}</td>
                  <td>{row.description}</td>
                </tr>
              ))}
            </tbody> */}
            <tbody>
  {tableData.map((row, index) => (
    <tr key={index}>
      <td>
        <Input
          type="number"
          value={row.productServiceId}
          onChange={(e) => handleTableRowChange(e, index, 'productServiceId')}
        />
      </td>
      <td>
        <Input
          type="number"
          value={row.workingHours}
          onChange={(e) => handleTableRowChange(e, index, 'workingHours')}
        />
      </td>
      <td>
        <Input
          type="number"
          value={row.rate}
          onChange={(e) => handleTableRowChange(e, index, 'rate')}
        />
      </td>
    </tr>
  ))}
</tbody>

          </Table>
        </FormGroup>
        <FormGroup>
          <Button color="primary" onClick={addRow}>
            Add Row
          </Button>
          {/* <Button color="secondary" onClick={cancelAction}>
            Download List
          </Button>
          <Button color="secondary" onClick={cancelAction}>
            Upload
          </Button> */}
        </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={cancelAction}>
            Cancel
          </Button>
          <Button color="primary" onClick={saveService}>
            Save
          </Button>
        </ModalFooter>
      </Modal>

      <Modal isOpen={showEditModal} toggle={cancelAction}>
        <ModalHeader toggle={cancelAction}>Edit Service</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="editServiceName">Service Name</Label>
              <Input
                type="text"
                placeholder="Enter service name"
                name="serviceName"
                value={selectedService ? selectedService.serviceName : ''}
                onChange={(e) =>
                  setSelectedService((prevService) => ({
                    ...prevService,
                    serviceName: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editPrice">Price</Label>
              <Input
                type="number"
                placeholder="Enter price"
                name="price"
                value={selectedService ? selectedService.price : ''}
                onChange={(e) =>
                  setSelectedService((prevService) => ({
                    ...prevService,
                    price: e.target.value,
                  }))
                }
              />
            </FormGroup>
            <FormGroup>
              <Label for="editDescription">Description</Label>
              <Input
                type="text"
                placeholder="Enter description"
                name="description"
                value={selectedService ? selectedService.description : ''}
                onChange={(e) =>
                  setSelectedService((prevService) => ({
                    ...prevService,
                    description: e.target.value,
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
          <Button color="primary" onClick={saveEditedService}>
            Save
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default Invoices;