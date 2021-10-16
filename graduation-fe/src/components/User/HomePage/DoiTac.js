//form "các đối tác quan trọng"
import { makeStyles } from '@material-ui/core/styles';
function DoiTac() {

     //css title
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
            marginLeft:'0.4rem',
            fontWeight: 'bold'
        }

    })); 
    const classes = useStyles();

    return (
        <div className="text-center pt-4 mt-5 mx-5" style={{height:'700px'}}>
            <h2  className={classes.title}>Các đối tác quan trọng của
            <span className={classes.span}>Smile Dental</span>
            </h2><p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p><br/><br/>
            <div id="carouselExampleControls" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img src="https://mdbootstrap.com/img/new/slides/003.jpg" className="d-block w-100"
                            style={{ width: '20rem', height: '18rem' }} alt="..." />
                    </div>
                    <div className="carousel-item">
                        <img src="https://mdbootstrap.com/img/new/slides/026.jpg" className="d-block w-100"
                            alt="..." style={{ width: '20rem', height: '18rem' }} />
                    </div>
                    <div className="carousel-item">
                        <img src="https://mdbootstrap.com/img/new/fluid/nature/011.jpg" className="d-block w-100"
                            alt="..." style={{ width: '20rem', height: '18rem' }} />
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    )
}
export default DoiTac;