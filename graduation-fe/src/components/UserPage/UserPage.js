import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

import { Switch, Route, Link, BrowserRouter } from 'react-router-dom';
import Book from './Book/Book';
import UserInformation from './UserInformation/UserInformation';
import DangNhap from '../DangNhap/DangNhap';
import DetailedNews from './News/DetailedNews';
import ShowDentist from './Dentist/ShowDentist';
import Register from './Register/Register';
import { Typography } from '@material-ui/core';
import MedicalRecords from './MedicalRecords/MedicalRecords';
import { useEffect, useState } from 'react';
import HomePage from './HomePage/HomePage';
import http from '../service/http-common';
import { useForm } from 'react-hook-form';
import axios from 'axios';

function UserPage() {
  const useStyles = makeStyles((theme) => ({
    title: {
      fontSize: '2.2em',
      marginBottom: '0.2em',
      color: '#1c1c1c',
      fontWeight: 300,
      fontFamily: 'Roboto',
    },
    span: {
      color: '#00BCD5',
      fontWeight: 'bold',
    },
  }));
  const classes = useStyles();

  const [isLogin, setIsLogin] = useState(localStorage.getItem('auth') !== null ? true : false);

  //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
  const [auth, setAuth] = useState({});
  useEffect(() => {
    let json = localStorage.getItem('auth');
    if (json != null && typeof json !== 'undefined') {
      setAuth(JSON.parse(json));
    }
  }, [isLogin]);

  //
  //LẤY THÔNG TIN CHI TIẾT NGƯỜI DÙNG KHI CÓ ACCOUNT_ID
  const [changePW, setchangePW] = useState({
    id: '',
    email: '',
    password: '',
    telephone: '',
    updateAt: '',
    rolesId: '',
    deleteAt: false,
  });
  const [account, setAccount] = useState({
    id: '',
    email: '',
    password: '',
    telephone: '',
    updateAt: '',
    rolesId: '',
    deleteAt: false,
  });
  useEffect(() => {
    if (typeof auth.id !== 'undefined' && auth.id != null) {
      // console.log(auth.id)
      http({
        url: '/accounts/' + auth.id,
        method: 'GET',
      })
        .then((response) => {
          const { data } = response;
          setAccount(data.data);
          setchangePW(data.data);
        })
        .catch((error) => {
          console.log(error, error.response);
        });
    }
  }, [auth]);

  //LOGOUT
  const btnLogout = () => {
    setAccount({
      id: '',
      email: '',
      password: '',
      telephone: '',
      updateAt: '',
      rolesId: '',
      deleteAt: false,
    });
    setIsLogin(false);
    localStorage.clear();
    sessionStorage.clear();
  };

  let token = localStorage.getItem('token');

  //doi mat khau

  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm();

  const onChangePW = (data) => {
    console.log('change PW');
    const yourDate = new Date();
    changePW.updateAt = yourDate.toISOString().split('T')[0];
    changePW.password = data.cpNewPassword;
    console.log(changePW);

    if (changePW.id !== '') {
      http({
        url: `http://localhost:8080/api/v1/accounts/chagepassword/${changePW.id}`,
        method: 'PUT',
        data: changePW,
      })
        .then((response) => {
          // const { data } = response;
          alert('Đổi mật khẩu thành công');
          window.location.reload();
        })
        .catch((error) => {
          console.log(error.response);
        });
    }
  };

  const onHandleSubmit = (data) => {
    data.email = account.email;
    // console.log(data);
    const url = 'http://localhost:8080/api/v1/user/auth';
    axios({
      url: url,
      method: 'POST',
      data: data,
    })
      .then((response) => {
        onChangePW(data);
      })
      .catch((error) => {
        alert('Sai mật khẩu');
      });
  };

  // const formPWEmail = register("email", { required: true });
  const formPWpassword = register('password', { required: true });
  const formPWnewPassword = register('newPassword', {
    required: 'Nhập mật khẩu mới',
    minLength: {
      value: 8,
      message: 'Mật khẩu phải có 8 chữ số',
    },
  });
  const formPWcpNewPassword = register('cpNewPassword', {
    required: 'Nhập lại mật khẩu mới',
    validate: (value) => value === watch('newPassword', '') || 'Mật khẩu mới không đúng',
  });

  return (
    <div>
      <BrowserRouter>
        {/* dau trang */}
        <nav
          className="navbar navbar-expand-lg navbar-light bg-light fixed-top"
          style={{ boxShadow: '1px 3px #f1f1f1' }}
        >
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">
              <img
                src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg"
                alt="logo"
                width="30"
                height="24"
              />
              <span className={classes.span}>Smile Dental</span>
            </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarTogglerDemo03"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse " id="navbarSupportedContent">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0 ">
                <li className="nav-item">
                  <Link
                    style={{
                      textTransform: 'uppercase',
                      textDecoration: 'none',
                      color: '#2c2c2c',
                      fontSize: '0.7142em',
                      padding: '0.5rem 0.7rem',
                      lineHeight: '3rem',
                      marginRight: '3px',
                    }}
                    to="/"
                  >
                    Trang chủ
                  </Link>
                </li>
                <li className="nav-item">
                  {token !== null ? (
                    <Link
                      style={{
                        textTransform: 'uppercase',
                        textDecoration: 'none',
                        color: '#2c2c2c',
                        fontSize: '0.7142em',
                        padding: '0.5rem 0.7rem',
                        lineHeight: '3rem',
                        marginRight: '3px',
                      }}
                      to="/book"
                    >
                      Đặt lịch
                    </Link>
                  ) : (
                    ''
                  )}
                </li>
                <li className="nav-item">
                  <Link
                    style={{
                      textTransform: 'uppercase',
                      textDecoration: 'none',
                      color: '#2c2c2c',
                      fontSize: '0.7142em',
                      padding: '0.5rem 0.7rem',
                      lineHeight: '3rem',
                      marginRight: '3px',
                    }}
                    to="/dentist"
                  >
                    Nha sĩ
                  </Link>
                </li>
                <li className="nav-item">
                  <Link
                    style={{
                      textTransform: 'uppercase',
                      textDecoration: 'none',
                      color: '#2c2c2c',
                      fontSize: '0.7142em',
                      padding: '0.5rem 0.7rem',
                      lineHeight: '3rem',
                      marginRight: '3px',
                    }}
                    to="/news"
                  >
                    Tin tức
                  </Link>
                </li>
                <li className="nav-item">
                  {token !== null ? (
                    <Link
                      style={{
                        textTransform: 'uppercase',
                        textDecoration: 'none',
                        color: '#2c2c2c',
                        fontSize: '0.7142em',
                        padding: '0.5rem 0.7rem',
                        lineHeight: '3rem',
                        marginRight: '3px',
                      }}
                      to="/medicalRecords"
                    >
                      Hồ sơ khám
                    </Link>
                  ) : (
                    ''
                  )}
                </li>
              </ul>

              <form>
                <div>
                  <input
                    type="search"
                    placeholder="Tìm kiếm"
                    aria-label="Search"
                    style={{ borderRadius: '10px', border: '1px solid gray', padding: '0.35rem' }}
                  />
                </div>
              </form>

              <div class="dropdown">
                <button
                  class="btn btn-secondary dropdown-toggle"
                  type="button"
                  id="dropdownMenuButton1"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Dropdown button
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                  <li>
                    <a class="dropdown-item" href="#">
                      Action
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      Another action
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      Something else here
                    </a>
                  </li>
                </ul>
              </div>

              <div className="navbar-nav" style={{ marginLeft: '1rem' }}>
                <li className="nav-item dropdown">
                  <span
                    className=" dropdown-toggle"
                    id="navbarDropdown"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <b>
                      {token !== null && account.email !== '' && typeof account.email != 'undefined'
                        ? 'Xin chào ' + account.email.substring(0, account.email.indexOf('@'))
                        : 'Xin chào, vui lòng đăng nhập'}
                    </b>
                    <img
                      src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg"
                      alt="logo"
                      width="30"
                      height="24"
                    />
                  </span>
                  <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                    {token === null ? (
                      <li>
                        <Link className="dropdown-item" to="/account/login">
                          Đăng nhập
                        </Link>
                      </li>
                    ) : (
                      <div>
                        <li>
                          <Link className="dropdown-item" to="/account/userInformation">
                            Thông tin cá nhân
                          </Link>
                        </li>
                        <li>
                          <hr className="dropdown-divider" />
                        </li>
                        <li>
                          <Link
                            className="dropdown-item"
                            to=""
                            data-bs-toggle="modal"
                            data-bs-target="#staticBackdrop"
                          >
                            Đổi mật khẩu
                          </Link>
                        </li>
                        <li>
                          <hr className="dropdown-divider" />
                        </li>
                        <li>
                          <Link className="dropdown-item" to="/" onClick={btnLogout}>
                            Đăng xuất
                          </Link>
                        </li>
                      </div>
                    )}
                    <li>
                      <hr className="dropdown-divider" />
                    </li>
                    <li>
                      <Link className="dropdown-item" to="/account/register">
                        Đăng ký
                      </Link>
                    </li>
                  </ul>
                </li>
              </div>
            </div>
          </div>
        </nav>

        {/* dau trang */}

        {/* <!-- Modal --> */}
        <div
          className="modal fade"
          id="staticBackdrop"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
          tabIndex="-1"
          aria-labelledby="staticBackdropLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="staticBackdropLabel">
                  Đổi mật khẩu
                </h5>
                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>

              <form onSubmit={handleSubmit(onHandleSubmit)}>
                <div className="row modal-body text-start">
                  <div className="col">
                    <div className="mt-2">
                      <label htmlFor="email" className="form-label">
                        Email:
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="email"
                        name="email"
                        placeholder="an@gmail.com"
                        value={account.email}
                        readOnly
                      />
                      <div>{errors.password && <span className="text-white">.</span>}</div>
                    </div>
                    <br />

                    <div className="mt-2">
                      <label htmlFor="newPassword" className="form-label">
                        Mật khẩu mới:
                      </label>
                      <input
                        type="password"
                        className="form-control"
                        id="newPassword"
                        name="newPassword"
                        {...formPWnewPassword}
                        onChange={(e) => {
                          formPWnewPassword.onChange(e);
                        }}
                      />
                      <div>
                        {errors.newPassword && (
                          <span className="text-danger">{errors.newPassword.message}</span>
                        )}
                      </div>
                    </div>

                    <br />
                  </div>
                  <div className="col">
                    <div className="mt-2">
                      <label htmlFor="password" className="form-label">
                        Mật khẩu hiện tại:
                      </label>
                      <input
                        type="password"
                        name="password"
                        className="form-control"
                        id="password"
                        {...formPWpassword}
                        onChange={(e) => {
                          formPWpassword.onChange(e);
                        }}
                      />
                      <div>
                        {errors.password && <span className="text-danger">Nhập mật khẩu</span>}
                      </div>
                    </div>
                    <br />
                    <div className="mt-2">
                      <label htmlFor="cpNewPassword" className="form-label">
                        Nhập lại mật khẩu mới:
                      </label>
                      <input
                        type="password"
                        className="form-control"
                        id="cpNewPassword"
                        name="cpNewPassword"
                        {...formPWcpNewPassword}
                        onChange={(e) => {
                          formPWcpNewPassword.onChange(e);
                        }}
                      />
                      <div>
                        {errors.cpNewPassword && (
                          <span className="text-danger">{errors.cpNewPassword.message}</span>
                        )}
                      </div>
                    </div>

                    <br />
                  </div>
                </div>

                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                    Huỷ
                  </button>
                  <button type="submit" className="btn btn-primary">
                    Đổi mật khẩu
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        {/* <!-- Modal --> */}

        {/* than trang */}
        <Typography component={'div'} style={{ minHeight: '1000px' }}>
          <Switch>
            <Route exact path="/">
              <HomePage />
            </Route>
            <Route path="/book">
              <Book />
            </Route>
            <Route path="/dentist">
              <ShowDentist />
            </Route>
            <Route path="/news">
              <DetailedNews />
            </Route>
            <Route path="/medicalRecords">
              <MedicalRecords />
            </Route>
            <Route path="/account/userInformation">
              <UserInformation />
            </Route>
            <Route path="/account/login">
              <DangNhap setIsLogin={setIsLogin} isLogin={isLogin} />
            </Route>
            <Route path="/account/register">
              <Register />
            </Route>
          </Switch>
        </Typography>
        {/* than trang */}

        {/* cuoi trang */}
        <footer>
          <div
            style={{
              background: '#f1f1f1',
              minHeight: '250px',
              paddingTop: '30px',
              textAlign: 'left',
              marginTop: '50px',
            }}
          >
            <div className="container">
              <div className="row col-sm-12  d-flex justify-content-center">
                <div className="col-sm-3 offset-1">
                  <img
                    src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg"
                    alt="logo"
                    width="120"
                    height="70"
                  />
                  <p>
                    Khám răng định kỳ giúp phát hiện các dấu hiệu cảnh báo sớm của một số vấn đề
                    liên quan đến sức khỏe. Hãy đến nha sĩ của bạn thường xuyên và giữ gìn sức khỏe.
                  </p>
                </div>
                <br />
                <div className="col-sm-3 offset-1">
                  <h5>LIÊN HỆ:</h5>
                  <i className="fas fa-home"></i> Địa chỉ: 113 Mỹ Đình, Hà Nội.
                  <br />
                  <i className="fas fa-tty"></i> Số điện thoại: 0123456789.
                  <br />
                  <i className="fas fa-envelope-open"></i> Email:{' '}
                  <a
                    className="text-dark"
                    style={{ textDecoration: 'none' }}
                    href="mailto:smiledentalfpt@gmail.com"
                  >
                    smiledentalfpt@gmail.com
                  </a>
                </div>
                <br />
                <div className="col-sm-3 offset-1">
                  <h5>LIÊN KẾT:</h5>
                  <i className="fab fa-facebook"></i> Facebook
                  <br />
                  <i className="fab fa-facebook-messenger"></i> Messenger
                  <br />
                  <i className="fab fa-twitter"></i> Twitter
                  <br />
                </div>
                <br />
              </div>
            </div>
            <div
              style={{
                background: '#353535',
                minHeight: '50px',
                textAlign: 'center',
                color: 'white',
                padding: '10px',
              }}
            >
              © Bản quyền thuộc về Smile Dental
            </div>
          </div>
        </footer>
        {/* cuoi trang */}
      </BrowserRouter>
    </div>
  );
}

export default UserPage;
