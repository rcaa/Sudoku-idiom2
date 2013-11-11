package solver;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import driver.util.Driver;
import driver.util.Util;

@Aspect
public class SolverDynamic {

	@Around("adviceexecution() && within(solver.SolverFeature)")
	public Object adviceexecutionIdiom(JoinPoint thisJoinPoint,
			ProceedingJoinPoint pjp) throws Throwable {
		Object ret;
		if (Driver.isActivated("solver")) {
			ret = pjp.proceed();
		} else {
			ret = Util.proceedAroundCallAtAspectJ(thisJoinPoint);
		}
		return ret;
	}
}
