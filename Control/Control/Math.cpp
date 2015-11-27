#include "Math.h"
#include <iostream>
#include <vector>

using namespace std;

double standardDeviation(vector<double> v, double m) {
	
	//Fields
	double size = v.size();
	double sumDif = 0; //The sum of the differences of each value and the mean
	double sd = 0; //Standard Deviation

	//Calculate the sum of the differences
	for (int i=0; i < size; i++) {
		sumDif += pow((v[i] - m), 2);
	}
	
	sd = abs ((sumDif) / (size-1));

	return sd;
}

double mean(vector<double> v) {
	double sum = 0;
	double size = v.size();

	for (int i = 0; i < size; i++) {
		sum += v[i];
	}

	double m = sum / size;

	return m;
}