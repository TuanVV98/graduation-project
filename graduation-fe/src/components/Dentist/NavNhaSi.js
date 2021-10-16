import { Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';

export default function NavNhaSi() {
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

    return (
        <div>

            <nav className="navbar nvbar-expand-md navbar-dark bd-dark" id="slidebar">

                <Link className="navbar-brand mx-4" to="/">
                 <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24"/><span className={classes.span}> Smile Dental</span>
                </Link>

                <input className="form-control mx-4 mt-4" type="search" placeholder="Tìm kiếm" aria-label="Search" style={{borderRadius: '10px'}}/>

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none" to="/" style={{fontSize: '15px',color:'white'}}>
                            <i className="fas fa-home"></i> <span > TRANG CHỦ</span>
                        </Link>
                    </div>
                </nav>

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none"  to="/thongTinBacSi" style={{fontSize: '15px',color:'white'}}>
                            <i className="fa fa-address-card"></i> <span > QUẢN LÝ THÔNG TIN CÁ NHÂN</span>
                        </Link>
                    </div>
                </nav>
                
                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none"  to="/lichLamViec" style={{fontSize: '15px',color:'white'}}>
                            <i className="fas fa-calendar-alt"></i> <span > QUẢN LÝ ĐƠN ĐẶT LỊCH</span>
                        </Link>
                    </div>
                </nav>

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1" >
                    <div className="container-fluid">
                        <Link className="text-decoration-none"  to="/thongKe" style={{fontSize: '15px',color:'white'}}>
                            <i className="fas fa-industry"></i> <span > THỐNG KÊ</span>
                        </Link>
                    </div>
                </nav>



                {/* Content1 */}
                <nav className="navbar navbar-dark bg-dark mt-1">
                    <div className="container-fluid">
                        <button className="navbar-toggler text-decoration-none" data-bs-toggle="collapse"
                            data-bs-target="#navbarToggleExternalContent"
                            aria-controls="navbarToggleExternalContent" aria-expanded="false"
                            aria-label="Toggle navigation" to="#" style={{fontSize: '15px',color:'white'}}>
                            <i className="fas fa-cog"></i><span> CÀI ĐẶT</span>
                        </button>
                    </div>
                </nav>

                <div className="collapse" id="navbarToggleExternalContent">
                    <div className="bg-dark p-4">
                        <ul className="navbar-nav  w-100">
                            <li className="nav-item">
                                <Link className="nav-link active" to="" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                    Đổi mật khẩu </Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link active" to="" >
                                    Đăng xuất</Link>
                            </li>
                        </ul>
                    </div>
                </div>


                {/* End Content 1 */}


                {/* Content2 */}
                {/* <nav className="navbar navbar-dark bg-dark">
                    <div className="container-fluid">
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarToggleExternalContent2"
                            aria-controls="navbarToggleExternalContent2" aria-expanded="false"
                            aria-label="Toggle navigation">
                            <i className="fa fa-truck"></i> ORDERS
                        </button>
                    </div>
                </nav>

                <div className="collapse" id="navbarToggleExternalContent2">
                    <div className="bg-dark p-4">
                        <ul className="navbar-nav flex-column w-100 justify-content-center">
                            <li className="nav-item">
                                <Link to=""
                                    className="nav-link active">Order List</Link>
                            </li>

                            <li className="nav-item">
                                <Link to=""
                                    className="nav-link active">Recycle Bin</Link>
                            </li>
                        </ul>
                    </div>
                </div> */}


                {/* End Content 2 */}




            </nav>





        </div>
    )
}