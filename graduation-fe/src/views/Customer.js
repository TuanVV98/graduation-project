import React from "react";
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';

import {
  Card,
  CardHeader,
  Row,
  Col,
} from "reactstrap";

import PanelHeader from "components/PanelHeader/PanelHeader.js";
import CustomerList from "components/Customer/CustomerList";
import CustomerForm from "components/Customer/CustomerForm";

function Voucher() {

  const [value, setValue] = React.useState('1');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <>
      <PanelHeader size="sm" />
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <Box sx={{ width: '100%', typography: 'body1'}}>
                  <TabContext value={value}>
                    <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                      <TabList onChange={handleChange} aria-label="lab API tabs example">
                        <Tab label="Quản lý" value="1" />
                        <Tab label="Danh sách" value="2" />
                      </TabList>
                    </Box>
                    <TabPanel value="1" sx={{marginLeft: '-2%'}}>
                        <CustomerForm />
                    </TabPanel>
                    <TabPanel value="2">
                        <CustomerList handleChange={handleChange} />
                    </TabPanel>
                  </TabContext>
                </Box>
              </CardHeader>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Voucher;
