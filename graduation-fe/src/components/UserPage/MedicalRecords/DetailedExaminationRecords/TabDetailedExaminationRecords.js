import InfoService from "./InfoService";
import StepByStep from "./StepByStep";
import ShowDetailMedicalRecords from "./ShowDetailMedicalRecords";
import InfoDentist from "./InfoDentist";


export default function TabDetailedExaminationRecords({setUpdateStatus}) {
    return (
        <div className="container mt-5">
            {/* Chi tiết hồ sơ khám */}
                <ShowDetailMedicalRecords/>
            {/*End Chi tiết hồ sơ khám */}
           
            <div className="row border border-dark mt-5">
                <StepByStep setUpdateStatus={setUpdateStatus}/>
            </div>

            {/* Thông Tin Nha Sĩ */}
                <InfoDentist/>
            {/*End Thông Tin Nha Sĩ */}

            {/* Thông tin dịch vụ */}
                <InfoService/>
            {/*End Thông tin dịch vụ */}

        </div>
    )
}