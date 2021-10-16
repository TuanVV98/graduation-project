import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "assets/scss/now-ui-dashboard.scss?v1.5.0";
import "assets/css/demo.css";
import Admin from "layouts/Admin";
import User from "layouts/User";

ReactDOM.render(
  
  <BrowserRouter>
    <Switch>
       <Route path="/admin" render={(props) => <Admin {...props} />} />
       <Route path="/user" render={(props) => <User />} />
       <Redirect to="/admin/homepage" />
    </Switch>
  </BrowserRouter>,
  document.getElementById("root")
);
