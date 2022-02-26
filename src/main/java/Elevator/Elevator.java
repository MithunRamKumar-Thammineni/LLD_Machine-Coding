package Elevator;

import javax.swing.plaf.TableHeaderUI;
import java.util.*;

public class Elevator {
    private int currentfloor = 0;
    private Direction current_direction = Direction.UP;
    private State current_state = State.IDLE;

    private TreeSet<Request> currentjobs = new TreeSet<>();
    private TreeSet<Request> up_pendingjobs = new TreeSet<>();
    private TreeSet<Request> down_pendingjobs = new TreeSet<>();

    public void startelevator() {
        while (true) {
            if (checkcurrentjobs()) {
                //check for upward direction
                if (current_direction == Direction.UP) {
                    Request request = currentjobs.pollFirst();
                    process_uprequest(request);
                    if (currentjobs.isEmpty())
                        addpendingdownjobstoCurrentjobs();

                }

                if (current_direction == Direction.DOWN) {
                    Request request = currentjobs.pollLast();
                    process_downrequest(request);
                    if (currentjobs.isEmpty())
                        addpendingupdjobstocurrentjobs();
                }
            }
        }
    }

    private void process_downrequest(Request request) {
        int start_floor = currentfloor;
        if (start_floor < request.getExternalRequest().getSourcefloor()) {
            for (int i = start_floor; i <= request.getExternalRequest().getSourcefloor(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We reached floor--" + i);
                currentfloor = i;
            }
        }
        System.out.println("Reached Source Floor--opening door:");
        start_floor = currentfloor;
        for (int i = start_floor; i >= request.getInternalRequest().getDestinationfloor(); i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("We have reached floor--" + i);
            currentfloor = i;
            if (checkNewJobcanbeprocessed(request))
                break;
        }
    }

    private void addpendingdownjobstoCurrentjobs() {
        if (!down_pendingjobs.isEmpty()) {
            currentjobs = down_pendingjobs;
            current_direction = Direction.DOWN;
        } else
            current_state = State.IDLE;
    }

    public void addpendingupdjobstocurrentjobs(){
        if(!up_pendingjobs.isEmpty())
        {
            currentjobs=up_pendingjobs;
            current_direction=Direction.UP;
        }
        else
            current_state=State.IDLE;
    }

    private void process_uprequest(Request request) {
        //First the elevator should reach to the requested floor
        int start_floor = currentfloor;
        if (start_floor < request.getExternalRequest().getSourcefloor()) {
            for (int i = start_floor; i <= request.getExternalRequest().getSourcefloor(); i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We have reached floor---" + i);
                currentfloor = i;
            }
        }
        System.out.println("Reached source floor--opening door");


        start_floor = currentfloor;
        for (int i = start_floor; i <= request.getInternalRequest().getDestinationfloor(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("We have reached floor--" + i);
            currentfloor = i;
            if (checkNewJobcanbeprocessed(request))
                break;

        }

    }

    private boolean checkNewJobcanbeprocessed(Request CurrentRequest) {
        if (checkcurrentjobs()) {
            if (current_direction == Direction.UP) {
                Request request = currentjobs.pollFirst();
                if (request.getInternalRequest().getDestinationfloor() < CurrentRequest.getInternalRequest().getDestinationfloor()) {
                    currentjobs.add(request);
                    currentjobs.add(CurrentRequest);
                    return true;
                }
                currentjobs.add(request);
            }
            if (current_direction == Direction.DOWN) {
                Request request = currentjobs.pollLast();
                if (request.getInternalRequest().getDestinationfloor() > CurrentRequest.getInternalRequest()
                        .getDestinationfloor()) {
                    currentjobs.add(request);
                    currentjobs.add(CurrentRequest);
                    return true;
                }
                currentjobs.add(request);

            }

        }
        return false;

    }

    public void add_job(Request request) {
        if (current_state == State.IDLE) {
            current_state = State.MOVING;
            current_direction = request.getExternalRequest().getDirectionToGo();
            currentjobs.add(request);
        } else if (current_state == State.MOVING) {
            if (request.getExternalRequest().getDirectionToGo() != current_direction)
                addtopendingjobs(request);
            else if (request.getExternalRequest().getDirectionToGo() == current_direction) {
                if (current_direction == Direction.UP && request.getInternalRequest().getDestinationfloor() < currentfloor) {
                    addtopendingjobs(request);
                } else if (current_direction == Direction.DOWN && request.getInternalRequest().getDestinationfloor() > currentfloor)
                    addtopendingjobs(request);
                else
                    currentjobs.add(request);
            }
        }
    }

    private void addtopendingjobs(Request request) {
        if (request.getExternalRequest().getDirectionToGo() == Direction.UP) {
            System.out.println("Added to pending jobs up");
            up_pendingjobs.add(request);
        } else if (request.getExternalRequest().getDirectionToGo() == Direction.DOWN) {
            System.out.println("Added to pending jobs down");
            down_pendingjobs.add(request);
        }
    }

    private boolean checkcurrentjobs() {
        return !currentjobs.isEmpty();
    }
}
