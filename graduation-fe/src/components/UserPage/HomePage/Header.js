import { makeStyles } from '@material-ui/core/styles';
import { Link } from 'react-router-dom';

function Header() {
    const useStyles = makeStyles(() => ({
        root: {
            boxShadow: 'none',
            border: '1px solid',
            lineHeight: 1.5,
            backgroundColor: '#00bcd5',
            borderColor: 'white',
            color: 'white',
            width: '10rem',
            height: '3rem',
            borderRadius: '5px',
            margin: '0.4rem',
            textDecoration:'none',
            paddingTop:'0.55rem',

            '&:hover': {
                backgroundColor: 'white',
                color: '#00bcd5',
                boxShadow: 'none',
                textDecoration:'none'

            },
        },
    }));
    const classes = useStyles();

    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";
    let token = localStorage.getItem("token");

    return (
        <div>

            <div id="carouselExampleCaptions" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img src={srcUrl + "cf81d7b7-b2bf-4266-bccd-a6a9bdec0889.jpg"}
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Smile Dental</h5>
                            <p>Bác sĩ khám răng cho bệnh nhân - Ảnh minh họa: element.</p>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <img src={srcUrl + "ebc9c737-53e4-4c1f-b1f3-8bbc78ab6c18.jpg"}
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Smile Dental</h5>
                            <p>Tập thể y bác sĩ khoa Răng trẻ em .</p>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <img src={srcUrl + "06d3b98d-192e-4bae-8503-3cf18d9740d2.jpg"}
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Smile Dental</h5>
                            <p>Khoa răng hàm mặt là một trong những chuyên khoa uy tín tại Smile Dental.</p>
                        </div>
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon btn-lg" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span className="carousel-control-next-icon btn-lg" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>

            </div>

            <div className=" text-center" style={{ clear: 'both', backgroundColor: '#00bcd5' }}>
                <span className="fs-3  text-white" style={{ fontFamily: 'Roboto' }}>
                    Thông Tin Liên Hệ: 0368686868 </span>

                <Link className={classes.root} type="button"
                    to={token === null ? "/account/login" : "/book"}>
                         Đặt lịch với nha sĩ
                </Link>


            </div>
        </div>
    )
}
export default Header;

