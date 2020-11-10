package com.amazonaws.lambda.survey.model;

import java.util.Objects;

public class SurveyResponse {

    private String preference;
    private String value;

    public SurveyResponse() {
    }

    public SurveyResponse(String preference, String value) {
        this.preference = preference;
        this.value = value;
    }

    public String getPreference() {
        return this.preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SurveyResponse preference(String preference) {
        this.preference = preference;
        return this;
    }

    public SurveyResponse value(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SurveyResponse)) {
            return false;
        }
        SurveyResponse surveyResponse = (SurveyResponse) o;
        return Objects.equals(preference, surveyResponse.preference) && Objects.equals(value, surveyResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preference, value);
    }

    @Override
    public String toString() {
        return "{" +
            " preference='" + getPreference() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }


}