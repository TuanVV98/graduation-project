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
  { id: 'name', label: 'Id', minWidth: 50 },
  { id: 'code', label: 'Nội dung', minWidth: 100 },
  {
    id: 'population',
    label: 'Ảnh/Video',
    minWidth: 50,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'size',
    label: 'Giảm giá',
    minWidth: 100,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'density',
    label: 'Ngày bắt đầu',
    minWidth: 150,
    align: 'right',
    format: (value) => value.toFixed(2),
  },
  {
    id: 'density',
    label: 'Ngày Kết thúc',
    minWidth: 170,
    align: 'right',
    format: (value) => value.toFixed(2),
  },
  {
    id: 'density',
    label: 'Ngày tạo',
    minWidth: 150,
    align: 'right',
    format: (value) => value.toFixed(2),
  }
];

function createData(name, code, population, size) {
  const density = population / size;
  return { name, code, population, size, density };
}

const rows = [
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
  createData('Id', 'Content', "image", "20%"),
];

export default function StickyHeadTable( {handleChange} ) {
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
