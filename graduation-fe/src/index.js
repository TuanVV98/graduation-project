import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "assets/scss/now-ui-dashboard.scss?v1.5.0";
import "assets/css/demo.css";
import Admin from "layouts/Admin";
import 'react-toastify/dist/ReactToastify.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import User from "layouts/User";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

ReactDOM.render(
  // <Provider store={store}>
    <BrowserRouter>
      <Switch>
        <Route path="/admin" render={(props) => <Admin {...props} />} />
        <Route path="/user" render={(props) => <User />} />
        <Redirect to="/user" />
      </Switch>
    </BrowserRouter>,
    // </ Provider>,
  document.getElementById("root")
);
