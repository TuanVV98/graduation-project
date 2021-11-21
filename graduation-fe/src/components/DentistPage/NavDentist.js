import { Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';

export default function NavDentist({ setIsLogin, setAccount }) {
    const useStyles = makeStyles((theme) => ({
        title: {
            fontSize: '2.2em',
            marginBottom: '0.2em',
            color: '#1c1c1c',
            fontWeight: 300,
            fontFamily: 'Roboto'
        },
        span: {
            color: '#00BCD5',
            fontWeight: 'bold'
        }
    }));
    const classes = useStyles();


    //LOGOUT
    const btnLogout = () => {
        setAccount({
            id: "",
            email: "",
            password: "",
            telephone: "",
            updateAt: "",
            rolesId: "",
            deleteAt: false
        });
        setIsLogin(false);
        localStorage.clear();
        sessionStorage.clear();

    }

    return (
        <div>

            <nav className="navbar nvbar-expand-md navbar-dark bd-dark" id="slidebar">

                <Link className="navbar-brand mx-4" to="/">
                    <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24" /><span className={classes.span}> Smile Dental</span>
                </Link>

                <input className="form-control mx-4 mt-4" type="search" placeholder="Tìm kiếm" aria-label="Search" style={{ borderRadius: '10px' }} />

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none" to="/" style={{ fontSize: '15px', color: 'white' }}>
                            <i className="fas fa-home"></i> <span > TRANG CHỦ</span>
                        </Link>
                    </div>
                </nav>

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none" to="/dentist-infomation" style={{ fontSize: '15px', color: 'white' }}>
                            <i className="fa fa-address-card"></i> <span > QUẢN LÝ THÔNG TIN CÁ NHÂN</span>
                        </Link>
                    </div>
                </nav>

                <nav className="navbar navbar-dark bg-dark mt-1 ms-1">
                    <div className="container-fluid">
                        <Link className="text-decoration-none" to="/work-schedule" style={{ fontSize: '15px', color: 'white' }}>
                            <i className="fas fa-calendar-alt"></i> <span > QUẢN LÝ ĐƠN ĐẶT LỊCH</span>
                        </Link>
                    </div>
                </nav>

                {/* <nav className="navbar navbar-dark bg-dark mt-1 ms-1" >
                    <div className="container-fluid">
                        <Link className="text-decoration-none" to="/statistcal" style={{ fontSize: '15px', color: 'white' }}>
                            <i className="fas fa-industry"></i> <span > THỐNG KÊ</span>
                        </Link>
                    </div>
                </nav> */}


                {/* Content1 */}
                <nav className="navbar navbar-dark bg-dark mt-1">
                    <div className="container-fluid mb-1">
                        <button className="navbar-toggler text-decoration-none" data-bs-toggle="collapse"
                            data-bs-target="#navbarToggleExternalContent"
                            aria-controls="navbarToggleExternalContent" aria-expanded="false"
                            aria-label="Toggle navigation" to="#" style={{ fontSize: '15px', color: 'white' }}>
                            <i className="fas fa-cog"></i><span> CÀI ĐẶT</span>
                        </button>
                    </div>

                    <div className="collapse" id="navbarToggleExternalContent">
                        <div className="bg-dark p-4">
                            <ul className="navbar-nav  w-100">
                                <li className="nav-item">
                                    <Link className="nav-link active" to="/"
                                        data-bs-toggle="modal"
                                        data-bs-target="#staticBackdrop"
                                    >
                                        Đổi mật khẩu </Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link active" to="/"
                                        onClick={btnLogout}>
                                        Đăng xuất</Link>
                                </li>
                            </ul>
                        </div>
                    </div>

                </nav>




                {/* End Content 1 */}

            </nav>

        </div>
    )
}