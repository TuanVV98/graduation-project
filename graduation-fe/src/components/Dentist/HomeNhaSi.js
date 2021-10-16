
import NavNhaSi from './NavNhaSi'
import Typography from '@material-ui/core/Typography';
import {
    Switch,
    Route,
    Link,
    BrowserRouter
} from 'react-router-dom';

import ThongTinBacSi from './ThongTinBacSi/ThongTinBacSi';
import ThongKe from './ThongKe/ThongKe';
import LichLamViec from './QLLichLamViec/LichLamViec';

export default function HomeNhaSi() {



    return (
        <BrowserRouter>
            <div className="container-fluid" >

                <div className="row min-vh-100 flex-column flex-md-row">

{/* sidebar */}
                    <aside className="col-12 col-md-3 col-xl-2 p-0 bg-dark flex-shrink-1">
                        <NavNhaSi />
                    </aside>
{/* sidebar */}
    
{/* container */}                
                    <main className="col-12 col-md-9 px-0 flex-grow-1">
                        <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{boxShadow:'1px 3px #f1f1f1'}}>
                        <div className="container-fluid">
                        <div className="collapse navbar-collapse " id="navbarSupportedContent">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0 "></ul>
                            <form className="d-flex">
                                <div className="navbar-nav">
                                <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <b>Nguyen Van An</b>
                                    <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24" />
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><Link className="dropdown-item" to=""  data-bs-toggle="modal" data-bs-target="#staticBackdrop">Đổi mật khẩu</Link></li>
                                    <li><Link className="dropdown-item" to="#">Đăng xuất</Link></li>
                                </ul>
                                </li>
                                </div>
                            </form>
                            </div>
                        </div>
                        </nav>

 {/* <!-- Modal --> */}
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
                        <div className="container py-3">
                            <article>
                                <Typography component="div" >
                                    <Switch>
                                        <Route path="/thongTinBacSi">
                                            <ThongTinBacSi/>
                                        </Route>
                                        <Route path="/thongKe">
                                            <ThongKe/>
                                        </Route>
                                        <Route path="/lichLamViec">
                                            <LichLamViec/>
                                        </Route>
                                    </Switch>
                                </Typography>
                            </article>
                        </div>
{/* than trang */}

                    </main>
{/* container */}
                </div>


            </div>
        </BrowserRouter>

    );
}