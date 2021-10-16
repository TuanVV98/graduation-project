import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
    Switch,
    Route,
    Link,
    BrowserRouter
  } from "react-router-dom";
import { Typography } from '@material-ui/core';
import routerUser from 'routerUser';

const User = (props) => {
    const useStyles = makeStyles((theme) => ({
        title: {
            fontSize : '2.2em',
            marginBottom: '0.2em',
            color: '#1c1c1c',
            fontWeight: 300,
            fontFamily: 'Roboto'
        },
        span:{
            color: '#00BCD5',
            fontWeight: 'bold'
        }
    })); 
const classes = useStyles();
    
    return(
        <div>
{/* dau trang */}
            <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top" style={{boxShadow:'1px 3px #f1f1f1'}}>
            <div className="container-fluid">
            <Link className="navbar-brand" to="/">
                 <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24"/><span className={classes.span}>Smile Dental</span>
            </Link>

            <div className="collapse navbar-collapse " id="navbarSupportedContent">

                <ul className="navbar-nav me-auto mb-2 mb-lg-0 ">
                    <li className="nav-item">
                    <Link className="nav-link active"  to="/">Trang chủ</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" to="/datLich">Đặt lịch</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" to="/nhaSi">Nha sĩ</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" to="/tinTuc">Tin tức</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link active" to="/hoSoKham">Hồ sơ khám</Link>
                    </li>
                </ul>

                <form className="d-flex">
                    <input className="form-control me-2" type="search" placeholder="Tìm kiếm" aria-label="Search" style={{borderRadius: '10px'}}/>

                    <div className="navbar-nav">
                    <li className="nav-item dropdown">
                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <b>Nguyen Van An</b>
                        <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24" />
                    </a>
                    <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><Link className="dropdown-item" to="/taiKhoan/dangNhap">Đăng nhập</Link></li>
                        <li><Link className="dropdown-item" to="/taiKhoan/dangKy">Đăng ký</Link></li>
                        <li><hr className="dropdown-divider"/></li>
                        <li><Link className="dropdown-item" to="/taiKhoan/thongTinNguoiDung">Thông tin cá nhân</Link></li>
                        <li><Link className="dropdown-item" to=""  data-bs-toggle="modal" data-bs-target="#staticBackdrop">Đổi mật khẩu</Link></li>
                        <li><Link className="dropdown-item" to="#">Đăng xuất</Link></li>
                    </ul>
                    </li>
                    </div>
                </form>
                </div>
            </div>
            </nav>
            
                    <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form >
                        <div className="row modal-body text-start">
                            <div className="col">
                                <div className="mt-2">
                                    <label htmlFor="email" className="form-label">Email:</label>
                                    <input type="text" className="form-control" id="email" placeholder="an@gmail.com"/>
                                </div><br/>
                                <div>
                                    <label htmlFor="password" className="form-label">Mật khẩu hiện tại:</label>
                                    <input type="password" className="form-control" id="password" />
                                </div><br/>
                            </div>
                            <div className="col">
                                <div className="mt-2">
                                    <label htmlFor="newPassword" className="form-label">Mật khẩu mới:</label>
                                    <input type="password" className="form-control" id="newPassword" />
                                </div><br/>
                                <div>
                                    <label htmlFor="CpnewPassword" className="form-label">Nhập lại mật khẩu mới:</label>
                                    <input type="password" className="form-control" id="CpnewPassword" />
                                </div><br/>
                            </div>
                        </div>

                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                            <button type="submit" className="btn btn-primary">Đổi mật khẩu</button>
                        </div>
                        </form>

                        </div>
                    </div>
                    </div>
                    {/* <!-- Modal --> */}

{/* than trang */}
            <Typography component={'div'} style={{minHeight:'1000px'}}>
                <Switch>
                    {routerUser.map((prop, key) => {
                        return (
                        <Route
                            path={prop.layout + prop.path}
                            component={prop.component}
                            key={key}
                        />
                        );
                    })}
                    <Redirect from="/admin" to="/admin/dashboard" />
                </Switch>
            </Typography>
{/* than trang */}

{/* cuoi trang */}
            <footer  style={{display: 'block',clear: 'both',width: '100%',marginTop:'50px'}}>
                <div style={{background: '#f1f1f1',height: '250px',paddingTop: '30px',textAlign:'left'}} className="row">
                    
                    <div className="col-sm-3 offset-1">
                        <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="120" height="70" />
                        <p>Khám răng định kỳ giúp phát hiện các dấu hiệu cảnh báo sớm của một số vấn đề liên quan đến sức khỏe. Hãy đến nha sĩ của bạn thường xuyên và giữ gìn sức khỏe.</p>
                    </div>

                    <div className="col-sm-3 offset-1">
                        <h5>LIÊN HỆ:</h5>	
                        <i className='fas fa-home'></i> Địa chỉ: 113 Mỹ Đình, Hà Nội.<br/>
                        <i className='fas fa-tty'></i> Số điện thoại: 0123456789.<br/>
                        <i className="fas fa-envelope-open"></i> Email: <a className="text-dark" style={{textDecoration: 'none'}} href="mailto:tuanvvph11914@fpt.edu.vn">SmileDental@gmail.com</a>
                    </div>
                    
                    <div className="col-sm-3 offset-1">
                        <h5>LIÊN KẾT:</h5>
                        <i className="fab fa-facebook"></i> Facebook<br/>
                        <i className="fab fa-facebook-messenger"></i> Messenger<br/>
                        <i className="fab fa-twitter"></i> Twitter<br/>
                    </div>
                </div>
                <div style={{background: '#353535',height: '50px',textAlign:'center',color:'white',padding: '10px'}}>© Bản quyền thuộc về Smile Dental</div>
            </footer>
        </div>
    )
}

export default User;