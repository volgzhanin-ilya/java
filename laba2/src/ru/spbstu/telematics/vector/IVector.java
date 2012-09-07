package ru.spbstu.telematics.vector;

/**
 * ����������������� ���������. ������� �������� � ������� � ������� ����������.
 */
public interface IVector<T> {

    /**
     * ��������� ������ � ���������
     * @param o
     */
    void add(T obj);
    /**
     * ��������� ������ � ��������� �� ��������� �������
     * @param o
     * @param pos
     */
    void add(T obj, int pos);
    /**
     * ������� ������ �� ���������, ����������� �� ��������� �������
     * @param index
     */
    boolean remove(int index);
    /**
     * ���������� ������, ����������� �� ������������ �������
     * @param index
     * @return
     * @throws Exception 
     */
    T get(int index);
    /**
     * ���������� ������ �������, ���� ����� ���� � �������. ���� ������ ���, �� -1.
     * @param o
     * @return
     */
    int indexOf(T obj);
}