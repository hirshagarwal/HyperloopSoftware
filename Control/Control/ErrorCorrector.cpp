#include <vector>
#include "ErrorCorrector.h"
#include "Math.h"

using namespace std;

//The error corrector will take 3 sensor readings per cycle

double correctTuple(vector<double> v)
{
	double m = 0;
	double sd = 0;
	double n = v.size();
	double finalValue = 0;
	vector<double> data = v;
	vector<double> normalReadings;
	

	//Compute mean of data
	m = mean(data);
	sd = standardDeviation(data, m);

	//Normalize readings
	for (int i = 0; i < n; i++) {
		double difference = abs (v[i]-m);
		if (difference < sd) {
			normalReadings.push_back(v[i]);
		}
	}

	finalValue = mean(normalReadings);

	return sd;
}
