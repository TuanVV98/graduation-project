import InfoService from "./InfoService";
import StepByStep from "./StepByStep";
import ShowWorkingScheduleDetails from "./ShowWorkingScheduleDetails";
import InfoCustomer from "./InfoCustomer";


export default function TabDetailedWorkSchedule({setUpdateList,updateList}) {
    return (
        <div className="container mt-5">
            {/* Chi tiết hồ sơ khám */}
                <ShowWorkingScheduleDetails updateList={updateList}/>
            {/*End Chi tiết hồ sơ khám */}
           
            {/* Trạng thái */}
            <div className="row border border-dark mt-5">
                <StepByStep setUpdateList={setUpdateList}/>
            </div>
            {/* End Trạng thái */}

            {/* Thông Tin Khách Hàng*/}
                <InfoCustomer/>
            {/*End Thông Tin Khách Hàng */}

            {/* Thông tin dịch vụ */}
                <InfoService/>
            {/*End Thông tin dịch vụ */}

        </div>
    )
}