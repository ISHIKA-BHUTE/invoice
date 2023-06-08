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

const Services = () => {
  const [services, setServices] = useState([]);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedService, setSelectedService] = useState(null);
  const [newService, setNewService] = useState({
    serviceName: '',
    price: 0,
    description: '',
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
  };

  return (
    <div style={{ padding: '10% 5%' }}>
      <h1>SERVICES</h1>
      <Button
        color="primary"
        size="sm"
        onClick={addService}
        style={{ marginBottom: '7px', marginLeft: 'auto', display: 'block' }}
      >
        Add Service
      </Button>

      <Table striped bordered hover style={{ backgroundColor: '#f2f2f2' }}>
        <thead>
          <tr>
            <th>Service Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id}>
              <td>{service.serviceName}</td>
              <td>{service.price}</td>
              <td>{service.description}</td>
              <td>
                <Button
                  color="secondary"
                  size="sm"
                  onClick={() => editService(service)}
                >
                  Edit
                </Button>{' '}
                <Button
                  color="dark"
                  size="sm"
                  onClick={() => deleteService(service)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal isOpen={showAddModal} toggle={cancelAction}>
        <ModalHeader toggle={cancelAction}>Add Service</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="serviceName">Service Name</Label>
              <Input
                type="text"
                placeholder="Enter service name"
                name="serviceName"
                value={newService.serviceName}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="price">Price</Label>
              <Input
                type="number"
                placeholder="Enter price"
                name="price"
                value={newService.price}
                onChange={handleInputChange}
              />
            </FormGroup>
            <FormGroup>
              <Label for="description">Description</Label>
              <Input
                type="text"
                placeholder="Enter description"
                name="description"
                value={newService.description}
                onChange={handleInputChange}
              />
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

export default Services;

