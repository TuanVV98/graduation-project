import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import AddIcon from '@mui/icons-material/Add';
import Button from '@mui/material/Button';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import SearchIcon from '@mui/icons-material/Search';
import {
    CardBody,
    FormGroup,
    Form,
    Input,
    Row,
    Col
  } from "reactstrap";

const columns = [
  { id: 'id', label: 'Id', minWidth: 50 },
  { id: 'email', label: 'Email', minWidth: 100 },
  {
    id: 'phone_number',
    label: 'Số điện thoại',
    minWidth: 50,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'author',
    label: 'Phân quyền',
    minWidth: 100,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  }
];

function createData(id, email, phone_number, author) {
  return { id, email, phone_number, author };
}

const rows = [
  createData('1', 'user@gmail.com', "0123456789", "Quản trị viên"),
  createData('2', 'user@gmail.com', "0123456789", "Quản trị viên"),
  createData('3', 'user@gmail.com', "0123456789", "Quản trị viên"),
  createData('4', 'user@gmail.com', "0123456789", "Quản trị viên"),
  createData('5', 'user@gmail.com', "0123456789", "Quản trị viên"),
  createData('6', 'user@gmail.com', "0123456789", "Quản trị viên"),
];

export default function AccountList( {handleChange} ) {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
      <div>
        <Button variant="contained" onClick={ e => handleChange(e, "1")} style={{marginBottom: '1em'}}>
            <AddIcon />
            Thêm
        </Button>
        
        <Row style={{marginTop: '1%'}}>
            <Col className="pr-1" md="12">
                <FormGroup>
                
                <Input
                    label={<SearchIcon />}
                    type="text" placeholder="Search"
                />
                </FormGroup>
            </Col>
        </Row>
        <Paper sx={{ width: '100%', overflow: 'hidden' }}>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table">
                <TableHead>
                    <TableRow>
                    {columns.map((column) => (
                        <TableCell
                        key={column.id}
                        align={column.align}
                        style={{ minWidth: column.minWidth }}
                        >
                        {column.label}
                        </TableCell>
                    ))}
                    <TableCell style={{ minWidth: 250 }}>
                        Thao tác
                    </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row) => {
                        return (
                        <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                            {columns.map((column) => {
                            const value = row[column.id];
                            return (
                                <TableCell key={column.id} align={column.align}>
                                {column.format && typeof value === 'number'
                                    ? column.format(value)
                                    : value}
                                </TableCell>
                            );
                            })}
                            <TableCell style={{ minWidth: 150 }}>
                            <Button variant="contained" color="info">
                                <EditIcon />
                                Sửa
                            </Button>
                            <Button variant="contained" color="error" style={{marginLeft: '1em'}}>
                                <DeleteIcon />
                                Xoá
                            </Button>
                            </TableCell>
                        </TableRow>
                        );
                    })}
                </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </Paper>
    </div>
  );
}
