import { makeStyles } from '@material-ui/core/styles';

function Header() {
    const useStyles = makeStyles(() => ({
        root: {
            boxShadow: 'none',
            border: '1px solid',
            lineHeight: 1.5,
            backgroundColor: '#00bcd5',
            borderColor: 'white',
            color:'white',
            width: '10rem',
            height: '3rem',
            borderRadius: '5px',
            margin:'0.4rem',
          
            '&:hover': {
              backgroundColor: 'white',
              color:'#00bcd5',
              boxShadow: 'none',
            },
          },
      }));
    const classes = useStyles();
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
                        <img src="https://mdbootstrap.com/img/new/fluid/nature/012.jpg"
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>First slide label</h5>
                            <p>Some representative placeholder content for the first slide.</p>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <img src="https://mdbootstrap.com/img/new/standard/city/041.jpg"
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Second slide label</h5>
                            <p>Some representative placeholder content for the second slide.</p>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/Others/images/76.jpg"
                            className="d-block w-100" alt="..." style={{ width: '20rem', height: '45rem' }} />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Third slide label</h5>
                            <p>Some representative placeholder content for the third slide.</p>
                        </div>
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>

            </div>

            <div className=" text-center" style={{clear:'both',backgroundColor:'#00bcd5'}}>
                <span className="fs-3  text-white" style={{fontFamily:'Roboto'}}>
                     Thông Tin Liên Hệ: 0368686868 </span>
                {/* <button type="button" style={{marginBottom:'0.4rem'}} className="btn btn-outline-dark text-white border border-white">
                    Đặt lịch với nha sĩ</button > */}
                    <button className={classes.root}> Đặt lịch với nha sĩ</button>
   
            </div>
        </div>
    )
}
export default Header;

