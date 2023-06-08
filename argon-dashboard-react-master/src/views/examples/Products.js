
  
// //   const Products = () => {
// //     return (
// //       <>
// //         {/* <UserHeader /> */}
        
// //       </>
// //     );
// //   };
  
// //   export default Products;

// import React, { useState } from 'react';
// import { Table, Button, Modal, Form } from 'react-bootstrap';

// const Products = () => {
//   const [products, setProducts] = useState([]);

//   const [showAddModal, setShowAddModal] = useState(false);
//   const [showEditModal, setShowEditModal] = useState(false);
//   const [selectedProduct, setSelectedProduct] = useState(null);
//   const [newProduct, setNewProduct] = useState({
//     productName: '',
//     rate: 0,
//     quantity: 0,
//     weight: '0 kg',
//     color:'',
//     description:'',
//     totalRate:0,
//   });

//   const handleAddProduct = () => {
//     setShowAddModal(true);
//   };

//   const handleSaveProduct = () => {
//     const newProductId = products.length + 1;
//     const newProductWithId = { ...newProduct, id: newProductId };
//     setProducts([...products, newProductWithId]);
//     setShowAddModal(false);
//     setNewProduct({
//       productName: '',
//       rate: 0,
//       quantity: 0,
//       weight: '0 kg',
//       color:'',
//     description:'',
//     totalRate:0,
//     });
//   };

//   const handleEditProduct = (product) => {
//     setSelectedProduct(product);
//     setShowEditModal(true);
//   };

//   const handleSaveEditedProduct = () => {
//     setProducts((prevProducts) =>
//       prevProducts.map((product) =>
//         product.id === selectedProduct.id ? { ...selectedProduct } : product
//       )
//     );
//     setShowEditModal(false);
//     setSelectedProduct(null);
//   };

//   const handleDeleteProduct = (product) => {
//     setProducts((prevProducts) => prevProducts.filter((p) => p.id !== product.id));
//   };

//   const handleCancel = () => {
//     setShowAddModal(false);
//     setShowEditModal(false);
//     setSelectedProduct(null);
//     setNewProduct({
//       productName: '',
//       rate: 0,
//       quantity: 0,
//       weight: '0 kg',
//       color:'',
//     description:'',
//     totalRate:0,
      
//     });
//   };

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
//   };

//   return (
//     <>
//       {/* <div  className="d-flex flex-column align-items-center justify-content-center min-vh-100"> */}
//       <div style={{ padding: '10% 5%' }}>
//       <h1>PRODUCTS</h1>

//       <Button variant="dark" size="sm" onClick={handleAddProduct} style={{ marginBottom: '1rem', marginLeft: 'auto', display: 'block' }}>
//         Add Product
//       </Button>
//       <Table striped bordered hover style={{ backgroundColor: '#f2f2f2' }}>
//         <thead>
//           <tr>
//             <th>Product Name</th>
//             <th>Rate</th>
//             <th>Quantity</th>
//             <th>Weight</th>
//             <th>Color</th>
//             <th>Description</th>
//             <th>TotalRate</th>
//             <th>Actions</th>
//           </tr>
//         </thead>
//         <tbody>
//           {products.map((product) => (
//             <tr key={product.id}>
//               <td>{product.productName}</td>
//               <td>{product.rate}</td>
//               <td>{product.quantity}</td>
//               <td>{product.weight}</td>
//               <td>{product.color}</td>
//               <td>{product.description}</td>
//               <td>{product.totalRate}</td>
//               <td>
//                 <Button variant="secondary"  size="sm"onClick={() => handleEditProduct(product)}>
//                   Edit
//                 </Button>{' '}
//                 <Button variant="dark" size="sm" onClick={() => handleDeleteProduct(product)}>
//                   Delete
//                 </Button>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </Table>

    

//       <Modal show={showAddModal} onHide={handleCancel}>
//         <Modal.Header closeButton>
//           <Modal.Title>Add Product</Modal.Title>
//         </Modal.Header>
//         <Modal.Body>
//           <Form>
//             <Form.Group controlId="productName">
//               <Form.Label>Product Name</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter product name"
//                 name="productName"
//                 value={newProduct.productName}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="rate">
//               <Form.Label>Rate</Form.Label>
//               <Form.Control
//                 type="number"
//                 placeholder="Enter rate"
//                 name="rate"
//                 value={newProduct.rate}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="quantity">
//               <Form.Label>Quantity</Form.Label>
//               <Form.Control
//                 type="number"
//                 placeholder="Enter quantity"
//                 name="quantity"
//                 value={newProduct.quantity}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="weight">
//               <Form.Label>Weight</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter weight"
//                 name="weight"
//                 value={newProduct.weight}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="color">
//               <Form.Label>Color</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter color"
//                 name="color"
//                 value={newProduct.color}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="description">
//               <Form.Label>Description</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter description"
//                 name="description"
//                 value={newProduct.description}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//             <Form.Group controlId="totalRate">
//               <Form.Label>TotalRate</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter TotalRate"
//                 name="totalRate"
//                 value={newProduct.totalRate}
//                 onChange={handleInputChange}
//               />
//             </Form.Group>
//           </Form>
//         </Modal.Body>
//         <Modal.Footer>
//           <Button variant="secondary" onClick={handleCancel}>
//             Cancel
//           </Button>
//           <Button variant="primary" onClick={handleSaveProduct}>
//             Save
//           </Button>
//         </Modal.Footer>
//       </Modal>

//       <Modal show={showEditModal} onHide={handleCancel}>
//         <Modal.Header closeButton>
//           <Modal.Title>Edit Product</Modal.Title>
//         </Modal.Header>
//         <Modal.Body>
//           <Form>
//             <Form.Group controlId="editProductName">
//               <Form.Label>Product Name</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter product name"
//                 name="productName"
//                 value={selectedProduct ? selectedProduct.productName : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     productName: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editRate">
//               <Form.Label>Rate</Form.Label>
//               <Form.Control
//                 type="number"
//                 placeholder="Enter rate"
//                 name="rate"
//                 value={selectedProduct ? selectedProduct.rate : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     rate: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editQuantity">
//               <Form.Label>Quantity</Form.Label>
//               <Form.Control
//                 type="number"
//                 placeholder="Enter quantity"
//                 name="quantity"
//                 value={selectedProduct ? selectedProduct.quantity : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     quantity: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editWeight">
//               <Form.Label>Weight</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter weight"
//                 name="weight"
//                 value={selectedProduct ? selectedProduct.weight : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     weight: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editColor">
//               <Form.Label>Color</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter color"
//                 name="color"
//                 value={selectedProduct ? selectedProduct.color : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     city: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editDescription">
//               <Form.Label>Description</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter description"
//                 name="description"
//                 value={selectedProduct ? selectedProduct.description : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     city: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//             <Form.Group controlId="editTotalRate">
//               <Form.Label>TotalRate</Form.Label>
//               <Form.Control
//                 type="text"
//                 placeholder="Enter totalRate"
//                 name="totalRate"
//                 value={selectedProduct ? selectedProduct.totalRate : ''}
//                 onChange={(e) =>
//                   setSelectedProduct((prevProduct) => ({
//                     ...prevProduct,
//                     city: e.target.value,
//                   }))
//                 }
//               />
//             </Form.Group>
//           </Form>
//         </Modal.Body>
//         <Modal.Footer>
//           <Button variant="secondary" onClick={handleCancel}>
//             Cancel
//           </Button>
//           <Button variant="primary" onClick={handleSaveEditedProduct}>
//             Save
//           </Button>
//         </Modal.Footer>
//       </Modal>
//       </div>
//     </>
//   );
// };

// export default Products;

import React, { useState } from 'react';
import { Table, Button, Modal, Form } from 'react-bootstrap';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [newProduct, setNewProduct] = useState({
    name: '',
    rate: 0,
    quantity: 0,
    weight: '0 kg',
    color: '',
    description: '',
    totalRate: 0,
  });

  const handleAddProduct = () => {
    setShowAddModal(true);
  };

  const handleSaveProduct = () => {
    const newProductId = products.length + 1;
    const newProductWithId = { ...newProduct, id: newProductId };
    setProducts([...products, newProductWithId]);
    resetForm();
  };

  const handleEditProduct = (product) => {
    setSelectedProduct(product);
    setShowEditModal(true);
  };

  const handleSaveEditedProduct = () => {
    setProducts((prevProducts) =>
      prevProducts.map((product) =>
        product.id === selectedProduct.id ? { ...selectedProduct } : product
      )
    );
    resetForm();
  };

  const handleDeleteProduct = (product) => {
    setProducts((prevProducts) => prevProducts.filter((p) => p.id !== product.id));
  };

  const handleCancel = () => {
    resetForm();
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
  };

  const resetForm = () => {
    setShowAddModal(false);
    setShowEditModal(false);
    setSelectedProduct(null);
    setNewProduct({
      name: '',
      rate: 0,
      quantity: 0,
      weight: '0 kg',
      color: '',
      description: '',
      totalRate: 0,
    });
  };

  return (
    <div style={{ padding: '10% 5%' }}>
      <h1>PRODUCTS</h1>

      <Button variant="primary" size="sm" onClick={handleAddProduct} style={{ marginBottom: "7px", marginLeft: 'auto', display: 'block' }}>
        Add Product
      </Button>

      <Table striped bordered hover style={{ backgroundColor: '#ffffff' }}>
        <thead>
          <tr>
            <th>Product Name</th>
            <th>Rate</th>
            <th>Quantity</th>
            <th>Weight</th>
            <th>Color</th>
            <th>Description</th>
            <th>Total Rate</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.rate}</td>
              <td>{product.quantity}</td>
              <td>{product.weight}</td>
              <td>{product.color}</td>
              <td>{product.description}</td>
              <td>{product.totalRate}</td>
              <td>
                <Button variant="secondary" size="sm" onClick={() => handleEditProduct(product)}>
                  Edit
                </Button>{' '}
                <Button variant="dark" size="sm" onClick={() => handleDeleteProduct(product)}>
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={showAddModal} onHide={handleCancel}>
        <Modal.Header closeButton>
          <Modal.Title>Add Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="productName">
              <Form.Label>Product Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter product name"
                name="name"
                value={newProduct.name}
                onChange={handleInputChange}
              />
            </Form.Group>
            <Form.Group controlId="editRate">
               <Form.Label>Rate</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter rate"
                name="rate"
                value={selectedProduct ? selectedProduct.rate : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    rate: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group controlId="editQuantity">
              <Form.Label>Quantity</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter quantity"
                name="quantity"
                value={selectedProduct ? selectedProduct.quantity : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    quantity: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group controlId="editWeight">
              <Form.Label>Weight</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter weight"
                name="weight"
                value={selectedProduct ? selectedProduct.weight : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    weight: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group controlId="editColor">
              <Form.Label>Color</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter color"
                name="color"
                value={selectedProduct ? selectedProduct.color : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    city: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group controlId="editDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter description"
                name="description"
                value={selectedProduct ? selectedProduct.description : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    city: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group controlId="editTotalRate">
              <Form.Label>TotalRate</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter totalRate"
                name="totalRate"
                value={selectedProduct ? selectedProduct.totalRate : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    city: e.target.value,
                  }))
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancel}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSaveProduct}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showEditModal} onHide={handleCancel}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="editProductName">
              <Form.Label>Product Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter product name"
                name="name"
                value={selectedProduct ? selectedProduct.name : ''}
                onChange={(e) =>
                  setSelectedProduct((prevProduct) => ({
                    ...prevProduct,
                    name: e.target.value,
                  }))
                }
              />
            </Form.Group>
            {/* ... Other form inputs */}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancel}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSaveEditedProduct}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Products;
